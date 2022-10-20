package mx.edu.utez.personalservice.model.personal;

import mx.edu.utez.personalservice.model.Repository;
import mx.edu.utez.personalservice.model.position.BeanPosition;
import mx.edu.utez.personalservice.utils.MySQL;

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
    public BeanPersonal findOne(Long id) {
        return null;
    }

    @Override
    public boolean save(BeanPersonal object) {
        return false;
    }

    @Override
    public boolean update(BeanPersonal object) {
        return false;
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }
}
