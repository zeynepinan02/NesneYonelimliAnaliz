package Database;

public class DatabaseFactory {
    public static IDatabaseManager getDatabase(String tip){

        if("Postgres".equalsIgnoreCase(tip)) return new PostgresDatabaseManager();

        return null;
    }
}
