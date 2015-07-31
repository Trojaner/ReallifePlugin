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

package de.static_interface.reallifeplugin.module.contract.database.row;

import de.static_interface.reallifeplugin.database.Row;

import javax.annotation.Nullable;

public class ContractRow implements Row {
    public Integer id;
    public String name;
    public int creator;
    public String content;
    public int type;
    public String events;
    public String user_ids;
    @Nullable
    public Long period;
    public long creation_time;
    public long expire_time;
}
