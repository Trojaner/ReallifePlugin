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

package de.static_interface.reallifeplugin.database.impl;

import com.mysema.query.sql.H2Templates;
import com.mysema.query.sql.SQLTemplates;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.static_interface.reallifeplugin.database.Database;
import de.static_interface.reallifeplugin.database.DatabaseConfiguration;
import de.static_interface.reallifeplugin.database.DatabaseType;
import de.static_interface.sinklibrary.api.annotation.Unstable;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.SQLException;

/** <b>Not used currently</b> */

@Unstable
public class H2Database extends Database {

    public H2Database(DatabaseConfiguration config, Plugin plugin) {
        super(config, plugin, DatabaseType.H2);
    }

    @Override
    public void setupConfig() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(10);
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("url", "jdbc:h2:file:" + new File(plugin.getDataFolder(), "database").getAbsolutePath()
                                            + ";MV_STORE=FALSE;MODE=MySQL;IGNORECASE=TRUE");
        config.setConnectionTimeout(5000);
        dataSource = new HikariDataSource(config);
    }

    @Override
    public void connect() throws SQLException {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            dataSource.close();
            throw e;
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }

        if (dataSource != null) {
            dataSource.close();
        }
    }

    @Override
    public SQLTemplates generateDialect() {
        return new H2Templates();
    }
}
