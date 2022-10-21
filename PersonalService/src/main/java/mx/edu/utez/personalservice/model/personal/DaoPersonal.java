package mx.edu.utez.personalservice.model.personal;

import mx.edu.utez.personalservice.model.Repository;
import mx.edu.utez.personalservice.model.position.BeanPosition;
import mx.edu.utez.personalservice.utils.MySQL;
import mx.edu.utez.personalservice.utils.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPersonal implements Repository<BeanPersonal> {
    Connection conn;
    PreparedStatement pstm;
    CallableStatement cstm;
    ResultSet rs;
    MySQL mysql = new MySQL();

    @Override
    public List<BeanPersonal> findAll() {
        List<BeanPersonal> personal = new ArrayList<>();
        BeanPersonal person = null;
        BeanPosition position = null;
        try {
            conn = mysql.getConnection();
            String query = "SELECT pe.*, po.description FROM personal pe "+
                    "JOIN position po ON pe.position_id = po.id;";
            pstm = conn.prepareStatement(query);
            rs =pstm.executeQuery();
            while (rs.next()){
                person = new BeanPersonal();
                position = new BeanPosition();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setLastname(rs.getString("lastname"));
                person.setBirthday(rs.getString("birthday"));
                person.setSalary(rs.getDouble("salary"));
                position.setDescription(rs.getString("description"));
                person.setPosition(position);
                personal.add(person);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE, "Error -> FindAll"+ e.getMessage());
        }finally {
            mysql.close(conn,pstm,rs);
        }
        return personal;
    }

    @Override
    public Response<BeanPersonal> findById(Long id) {
        Response<BeanPersonal> response = new Response<>();
        BeanPersonal person =null;
        BeanPosition position = null;

        try {
            conn = mysql.getConnection();
            String query = "SELECT pe.*, po.description FROM personal pe "+
                    "JOIN position po ON pe.position_id = po.id WHERE pe.id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1,id);
            rs =pstm.executeQuery();
            if (rs.next()){
                person = new BeanPersonal();
                position = new BeanPosition();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setLastname(rs.getString("lastname"));
                person.setBirthday(rs.getString("birthday"));
                person.setSalary(rs.getDouble("salary"));
                position.setDescription(rs.getString("description"));
                person.setPosition(position);
                response.setError(false);
                response.setStatus(200);
                response.setMessage("Ok");
                response.setData(person);
            }else {
                response.setError(false);
                response.setStatus(200);
                response.setMessage("Nothing found");
                response.setData(null);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE, "Error en findById" + e.getMessage());
            response.setError(true);
            response.setStatus(400);
            response.setMessage("Error ->" + e.getMessage());
            response.setData(null);
        }finally {
            mysql.close(conn,pstm,rs);
        }
        return response;
    }

    @Override
    public Response<BeanPersonal> save(BeanPersonal person) {
        Response<BeanPersonal> response = new Response<>();
        try {
            conn = mysql.getConnection();
            String query = "INSERT INTO personal (name, surname, lastname, salary,birthday,position_id)" +
                    "VALUES (?,?,?,?,?,?)";
            pstm = conn.prepareStatement(query);

            pstm.setString(1, person.getName());
            pstm.setString(2, person.getSurname());
            pstm.setString(3, person.getLastname());
            pstm.setDouble(4,person.getSalary());
            pstm.setString(5 , person.getBirthday());
            pstm.setLong(6, person.getPosition().getId());

           if (pstm.executeUpdate() == 1){
               response.setError(false);
               response.setStatus(200);
               response.setMessage("Persona registrada correctamente");
               response.setData(person);
           }else {
               response.setError(true);
               response.setStatus(200);
               response.setMessage("No se registro correctamente");
               response.setData(null);
           }

        }catch (SQLException | NullPointerException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE, "Error en save" + e.getMessage());
            response.setError(true);
            response.setStatus(400);
            response.setMessage("Error -> " + e.getMessage());
            response.setData(null);
        }finally {
            mysql.close(conn,pstm,rs);
        }


        return response;
    }

    @Override
    public Response<BeanPersonal> update(BeanPersonal person) {
        Response<BeanPersonal> response = new Response<>();
        try {
            conn = mysql.getConnection();
            String query = "UPDATE personal SET name = ?, surname = ?,lastname= ? ,salary = ? , birthday = ?, position_id = ? WHERE personal.id =?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, person.getName());
            pstm.setString(2, person.getSurname());
            pstm.setString(3, person.getLastname());
            pstm.setDouble(4,person.getSalary());
            pstm.setString(5 , person.getBirthday());
            pstm.setLong(6, person.getPosition().getId());
            pstm.setLong(7,person.getId());
            if (pstm.executeUpdate() == 1){
                response.setError(false);
                response.setStatus(200);
                response.setMessage("Persona acutializada correctamente");
                response.setData(person);
            }else {
                response.setError(true);
                response.setStatus(200);
                response.setMessage("No se actualizo correctamente");
                response.setData(null);
            }

        }catch (SQLException | NullPointerException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE, "Error en save" + e.getMessage());
            response.setError(true);
            response.setStatus(400);
            response.setMessage("Error ->" + e.getMessage());
            response.setData(null);
        } finally {
            mysql.close(conn,pstm,rs);
        }


        return response;
    }

    @Override
    public Response<BeanPersonal> delete(Long id) {
        return null;
    }
}
