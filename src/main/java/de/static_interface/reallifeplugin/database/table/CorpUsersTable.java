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

package de.static_interface.reallifeplugin.database.table;

import de.static_interface.reallifeplugin.database.Database;
import de.static_interface.reallifeplugin.database.table.row.CorpUserRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CorpUsersTable extends Table<CorpUserRow> {

    public CorpUsersTable(Database db) {
        super("CorpUsers", db);
    }

    @Override
    public void create() throws SQLException {
        String sql =
                "CREATE TABLE IF NOT EXISTS `" + db.getConfig().getTablePrefix() + getName() + "` ("
                + "`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "`corp_id` INT,"
                + "`isCoCeo` TINYINT(0) NOT NULL,"
                + "`join_time` BIGINT NOT NULL,"
                + "`rank` VARCHAR(36) NOT NULL,"
                + "`uuid` VARCHAR(36) NOT NULL,"
                + "INDEX `corp_id_I` (`corp_id`),"
                + "INDEX `isCoCeo_I` (`isCoCeo`)"
                + ");";
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public ResultSet serialize(CorpUserRow row) throws SQLException {
        if (row.id != null) {
            throw new IllegalArgumentException("Id should be null!");
        }

        String sql = "INSERT INTO `{TABLE}` VALUES(NULL, ?, ?, ?, ?, ?);";
        return executeQuery(sql, row.corpId, row.isCoCeo, row.joinTime, row.rank, row.uuid.toString());
    }

    @Override
    public CorpUserRow[] deserialize(ResultSet rs) throws SQLException {
        int rowcount = 0;
        if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }

        CorpUserRow[] rows = new CorpUserRow[rowcount];
        int i = 0;
        while (rs.next()) {
            CorpUserRow row = new CorpUserRow();
            if (hasColumn(rs, "id")) {
                row.id = rs.getInt("id");
            }
            if (hasColumn(rs, "corp_id")) {
                row.corpId = rs.getInt("corp_id");
                if (rs.wasNull()) {
                    row.corpId = null;
                }
            }
            if (hasColumn(rs, "isCoCeo")) {
                row.isCoCeo = rs.getBoolean("isCoCeo");
            }
            if (hasColumn(rs, "join_time")) {
                row.joinTime = rs.getLong("join_time");
            }
            if (hasColumn(rs, "rank")) {
                row.rank = rs.getString("rank");
            }
            if (hasColumn(rs, "uuid")) {
                row.uuid = UUID.fromString(rs.getString("uuid"));
            }
            rows[i] = row;
            i++;
        }
        return rows;
    }
}
