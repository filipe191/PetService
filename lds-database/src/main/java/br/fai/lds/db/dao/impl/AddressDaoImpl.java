package br.fai.lds.db.dao.impl;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.AddressDao;
import br.fai.lds.model.entities.AddressModel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao<AddressModel> {
    @Override
    public List<AddressModel> find() {

        List<AddressModel> items = new ArrayList<>();

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM endereco";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            //colocar o nome da coluna do BD
            while (resultSet.next()) {
                AddressModel address = new AddressModel();
                address.setId(resultSet.getInt("id"));
                address.setLogradouro(resultSet.getString("logradouro"));
                address.setNumero(resultSet.getInt("numero"));
                address.setCEP(resultSet.getInt("cep"));
                address.setBairro(resultSet.getString("bairro"));
                items.add(address);
            }


        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return items;
    }

    @Override
    public AddressModel findById(int id) {

        AddressModel item = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM endereco where id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new AddressModel();
                item.setId(resultSet.getInt("id"));
                item.setLogradouro(resultSet.getString("logradouro"));
                item.setNumero(resultSet.getInt("numero"));
                item.setCEP(resultSet.getInt("cep"));
                item.setBairro(resultSet.getString("bairro"));
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
    public int create(AddressModel entity) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "INSERT INTO public.endereco(	id, logradouro, numero, cep, bairro, usuario_id, municipio_id)	VALUES (default, ?, ?, ?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getLogradouro());
            preparedStatement.setString(2, entity.getBairro());
            preparedStatement.setInt(3, entity.getCEP());


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
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection,resultSet);
        }
    }


    @Override
    public boolean update(AddressModel entity) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "UPDATE public.endereco	SET id=?, logradouro=?, numero=?, cep=?, bairro=?, usuario_id=?, municipio_id=?;";


        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getLogradouro());
            preparedStatement.setInt(3, entity.getCEP());
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
        final String sql = "DELETE FROM public.endereco WHERE id = ?;";

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
}
