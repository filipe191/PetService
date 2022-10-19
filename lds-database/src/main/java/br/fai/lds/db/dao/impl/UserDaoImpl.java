package br.fai.lds.db.dao.impl;
import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.UserDao;
import br.fai.lds.model.entities.UserModel;
import br.fai.lds.model.enums.UserType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao<UserModel> {


    @Override
    public List<UserModel> find() {

        List<UserModel> items = new ArrayList<>();

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM usuario ;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("senha"));
                user.setCpf(resultSet.getString("cpf"));
                user.setFullname(resultSet.getString("nome"));
                user.setTelefone(resultSet.getString("telefone"));
                user.setDataNasc(resultSet.getTimestamp("data_nascimento"));

                String userType = resultSet.getString("tipo");
                user.setType(UserType.valueOf(userType));

                items.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try catch comando para o try catch ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return items;
    }


    @Override
    public UserModel findById(int id) {

        UserModel item = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM usuario where id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new UserModel();
                item.setUsername(resultSet.getString("username"));
                item.setEmail(resultSet.getString("email"));
                item.setPassword(resultSet.getString("senha"));
                item.setCpf(resultSet.getString("cpf"));
                item.setFullname(resultSet.getString("nome"));
                item.setTelefone(resultSet.getString("telefone"));

                String userType = resultSet.getString("tipo");
                item.setType(UserType.valueOf(userType));

                item.setDataNasc(resultSet.getTimestamp("data-nascimento"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return item;
    }

    @Override
    public int create(UserModel entity) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "INSERT INTO public.usuario(tid, username, email, senha, cpf, nome, telefone, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?);" ;

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getFullname());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, UserType.CLIENT.toString());
            preparedStatement.setBoolean(6, true);
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(8, entity.getUsername());
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));


            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
            return id;


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return id;
//            motivo do finally no try catch comando para o try catch ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection,resultSet);
        }
    }


    @Override
    public boolean update(UserModel entity) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "UPDATE public.usuario SET id=?, username=?, email=?, senha=?, cpf=?, nome=?, telefone=?, tipo=?, data_nascimento=?	WHERE <condition>;" ;

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getCpf());
            preparedStatement.setString(5, entity.getFullname());
            preparedStatement.setString(6, entity.getTelefone());
            preparedStatement.setString(7, UserType.CLIENT.toString());
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(4, entity.getId());


            preparedStatement.execute();
            connection.commit();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection);
        }
    }

    @Override
    public boolean deleteById(int id) {

        boolean result = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "DELETE FROM usuario WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            connection.setAutoCommit(false);

            preparedStatement.execute();

            connection.commit();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectionFactory.close(preparedStatement,connection);
        }

        return result;
    }


    @Override
    public UserModel validateUserNameAndPassword(String username, String password) {

        UserModel user = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM usuario WHERE username = ? and senha = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeQuery();

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullname(resultSet.getString("nome"));
                user.setEmail(resultSet.getString("email"));

                String userType = resultSet.getString("tipo");
                user.setType(UserType.valueOf(userType));

            }



        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement,connection,resultSet);
        }

        return user;
    }
}
