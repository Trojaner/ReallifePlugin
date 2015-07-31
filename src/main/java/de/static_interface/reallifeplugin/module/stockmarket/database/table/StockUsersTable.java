/*
 * Copyright (c) 2013 - 2015 <http://static-interface.de> and contributors
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.static_interface.reallifeplugin.module.stockmarket.database.table;

import de.static_interface.reallifeplugin.database.AbstractTable;
import de.static_interface.reallifeplugin.database.Database;
import de.static_interface.reallifeplugin.module.corporation.database.table.CorpUsersTable;
import de.static_interface.reallifeplugin.module.stockmarket.database.row.StockUserRow;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockUsersTable extends AbstractTable<StockUserRow> {

    public static final String TABLE_NAME = "stock_users";
    public StockUsersTable(Database db) {
        super(TABLE_NAME, db);
    }

    @Override
    public void create() throws SQLException {
        String sql;

        switch (db.getType()) {
            case H2:
                sql =
                        "CREATE TABLE IF NOT EXISTS " + getName() + " ("
                        + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        + "amount INT NOT NULL,"
                        + "stock_id INT NOT NULL,"
                        + "user_id INT NOT NULL,"
                        + "FOREIGN KEY (stock_id) REFERENCES " + db.getConfig().getTablePrefix() + StocksTable.TABLE_NAME
                        + "(id) ON UPDATE CASCADE ON DELETE CASCADE,"
                        + "FOREIGN KEY (user_id) REFERENCES " + db.getConfig().getTablePrefix() + CorpUsersTable.TABLE_NAME
                        + "(id) ON UPDATE CASCADE ON DELETE CASCADE,"
                        + "INDEX stock_id_I (stock_id),"
                        + "INDEX user_id_I (user_id)"
                        + ");";
                break;

            case MYSQL:
            default:
                sql =
                        "CREATE TABLE IF NOT EXISTS `" + getName() + "` ("
                        + "`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        + "`amount` INT NOT NULL,"
                        + "`stock_id` INT NOT NULL,"
                        + "`user_id` INT NOT NULL,"
                        + "FOREIGN KEY (`stock_id`) REFERENCES `" + db.getConfig().getTablePrefix() + StocksTable.TABLE_NAME
                        + "`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,"
                        + "FOREIGN KEY (`user_id`) REFERENCES `" + db.getConfig().getTablePrefix() + CorpUsersTable.TABLE_NAME
                        + "`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,"
                        + "INDEX `stock_id_I` (`stock_id`),"
                        + "INDEX `user_id_I` (`user_id`)"
                        + ");";
                break;
        }

        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }
}
