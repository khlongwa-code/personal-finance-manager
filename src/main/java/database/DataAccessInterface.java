package database;

import java.time.LocalDateTime;

import net.lemnik.eodsql.BaseQuery;
import net.lemnik.eodsql.Select;
import net.lemnik.eodsql.Update;

public interface DataAccessInterface extends BaseQuery {
    @Update("CREATE TABLE IF NOT EXISTS users ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "name TEXT NOT NULL, "
        + "surname TEXT NOT NULL, "
        + "email TEXT NOT NULL UNIQUE, "
        + "password TEXT NOT NULL"
        +")")
    public void createUsersTable();

    @Update("CREATE TABLE IF NOT EXISTS income ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "user_id INTEGER, "
        + "amount REAL NOT NULL, "
        + "source TEXT, "
        + "date_received DATETIME, "
        + "FOREIGN KEY(user_id) REFERENCES users(id)"
        +")")
    public void createIncomeTable();

    @Update("CREATE TABLE IF NOT EXISTS expenses ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "user_id INTEGER, "
        + "amount REAL NOT NULL, "
        + "category TEXT, "
        + "date_spent DATETIME, "
        + "FOREIGN KEY(user_id) REFERENCES users(id)"
        +")")
    public void createExpensesTable();

    @Update("CREATE TABLE IF NOT EXISTS savings ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "user_id INTEGER, "
        + "goal_name TEXT NOT NULL, "
        + "goal_amount REAL NOT NULL, "
        + "current_amount REAL, "
        + "start_date DATETIME, "
        + "end_date DATE, "
        + "FOREIGN KEY(user_id) REFERENCES users(id)"
        +")")
    public void createSavingsTable();

    @Update("CREATE TABLE IF NOT EXISTS budget ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "user_id INTEGER, "
        + "monthly_budget REAL NOT NULL, "
        + "date_created DATETIME, "
        + "FOREIGN KEY(user_id) REFERENCES users(id)"
        +")")
    public void createBudgetTable();

    @Update("CREATE TABLE IF NOT EXISTS transactions ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "user_id INTEGER, "
        + "amount REAL NOT NULL, "
        + "type TEXT, "  // Type: 'income' or 'expense'
        + "transaction_date DATETIME, "
        + "FOREIGN KEY(user_id) REFERENCES users(id)"
        + ")")
    public void createTransactionsTable();

    @Update("CREATE TABLE IF NOT EXISTS distributions ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "user_id INTEGER, "
        + "amount REAL NOT NULL, "
        + "type TEXT, "  // Type: 'income' or 'expense'
        + "transaction_date DATETIME, "
        + "FOREIGN KEY(user_id) REFERENCES users(id)"
        + ")")
    public void createDistributionTable();

    @Update("INSERT INTO users (name, surname, email, password) VALUES (?{1}, ?{2}, ?{3}, ?{4})")
    public void createUser(String name, String surname, String email, String password);

    @Select("SELECT id FROM users WHERE email = ?{1}")
    public Integer getUserId(String email);

    @Select("SELECT COUNT(*) FROM users WHERE email = ?{1}")
    public Integer emailExists(String email);

    @Select("SELECT password FROM users WHERE email = ?{1}")
    public String getUserPassword(String email);

    @Update("DELETE FROM users WHERE email = ?{1}")
    public void deleteDataByEmail(String email);

    @Update("INSERT INTO transactions (amount, type, transaction_date, user_id) VALUES (?{1}, ?{2}, ?{3}, ?{4})")
    public void makeTransaction(Float amount, String type, String date, int userId);

    @Update("INSERT INTO income (amount, source, date_received, user_id) VALUES (?{1}, ?{2}, ?{3}, ?{4})")
    public void makeIncome(Float amount, String source, String date, int userId);

    @Update("INSERT INTO expenses (amount, category, date_spent, user_id) VALUES (?{1}, ?{2}, ?{3}, ?{4})")
    public void makeExpense(Float amount, String category, String date, int userId);
}
