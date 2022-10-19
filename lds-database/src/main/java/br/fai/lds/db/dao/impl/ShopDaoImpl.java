package br.fai.lds.db.dao.impl;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.ShopDao;
import br.fai.lds.model.entities.ShopModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao<ShopModel> {
    @Override
    public List<ShopModel> find() {

        List<ShopModel> items = new ArrayList<>();

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM pedido";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ShopModel user = new ShopModel();
                user.setId(resultSet.getInt("id"));
                user.setQuantidade(resultSet.getString("quantidade"));
                user.setDataHora(resultSet.getTimestamp("data_hora"));
                user.setSituacao(resultSet.getString("situacao"));
                items.add(user);
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
    public ShopModel findById(int id) {

        ShopModel item = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM pedido where id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new ShopModel();
                item.setId(resultSet.getInt("id"));
                item.setQuantidade(resultSet.getString("quantidade"));
                item.setDataHora(resultSet.getTimestamp("data_hora"));
                item.setSituacao(resultSet.getString("situacao"));

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
    public int create(ShopModel entity) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "INSERT INTO public.pedido( id, quantidade, data_hora, situacao, anuncio_id, usuario_id) + VALUES (?, ?, ?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getQuantidade());


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
    public boolean update(ShopModel entity) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "UPDATE public.pedido SET id=?, quantidade=?, data_hora=?, situacao=?, anuncio_id=?, usuario_id=? WHERE <condition>;";


        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getQuantidade());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(3, entity.getSituacao());
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
        final String sql = "DELETE FROM pedido WHERE id = ?;";

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
