package br.fai.lds.db.dao.impl;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.CityDao;
import br.fai.lds.model.entities.CityModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao<CityModel> {
    @Override
    public List<CityModel> find() {
        List<CityModel> items = new ArrayList<>();

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM municipio";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CityModel city = new CityModel();
                city.setId(resultSet.getInt("id"));
                city.setCidade(resultSet.getString("cidade"));
                city.setUF(resultSet.getString("uf"));
                items.add(city);
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
    public CityModel findById(int id) {
        CityModel item = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM municipio where id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new CityModel();
                item.setId(resultSet.getInt("id"));
                item.setCidade(resultSet.getString("cidade"));
                item.setUF(resultSet.getString("uf"));
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
    public int create(CityModel entity) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "INSERT INTO public.municipio(	id, cidade, uf)	VALUES (?, ?, ?);";


        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getCidade());
            preparedStatement.setString(2, entity.getUF());


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
    public boolean update(CityModel entity) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "UPDATE public.municipio	SET id=?, cidade=?, uf=?;";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getUF());
            preparedStatement.setString(3, entity.getCidade());
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
        final String sql = "DELETE FROM public.usuario WHERE id = ?;";

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
