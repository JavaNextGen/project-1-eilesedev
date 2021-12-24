package com.revature.util;


import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConnectionFactoryTest {

    private static ConnectionFactory connectionFactory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
    }

    @Test
    public void testConnectionFactoryIsAbleToGetConnection() {
        Connection conn = null;
		try {
			conn = connectionFactory.getConnection();
		} catch (SQLException e) {
			System.out.println("Database connection test failed");
			e.printStackTrace();
		}

        assertThat(conn, instanceOf(Connection.class));
    }
}
