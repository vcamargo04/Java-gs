package br.com.fiap.rapidmed.model.repository;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectionFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static ConnectionFactory instance;
    private Connection connection;
    private String url;
    private String user;
    private String pass;
    private String driver;

    private ConnectionFactory(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = createInstance();
                }
            }
        }
        return instance;
    }

    private static ConnectionFactory createInstance() {
        Properties prop = new Properties();
        try (FileInputStream file = new FileInputStream("./src/main/resources/application.properties")) {
            prop.load(file);

            String url = prop.getProperty("datasource.url");
            String user = prop.getProperty("datasource.username");
            String pass = prop.getProperty("datasource.password");
            String driver = prop.getProperty("datasource.driver-class-name");

            return new ConnectionFactory(url, user, pass, driver);

        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    public Connection getConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            if (driver == null || driver.equals("") || url == null || url.equals("") || user == null || user.equals("")) {
                throw new RuntimeException("Invalid configuration");
            }
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error creating connection", e);
            throw new RuntimeException("Error creating connection", e);
        }
    }


    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
    }

		public String getUrl() {
			return url;
		}

		public String getUser() {
			return user;
		}

		public String getPass() {
			return pass;
		}

		public String getDriver() {
			return driver;
		}	
		
	}



