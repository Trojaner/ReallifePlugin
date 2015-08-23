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

package de.static_interface.reallifeplugin.module.politics.database.row;

import de.static_interface.sinklibrary.database.Row;
import de.static_interface.sinklibrary.database.annotation.Column;

import javax.annotation.Nullable;

public class PartyRow implements Row {

    @Column(autoIncrement = true, primaryKey = true)
    public Integer id;

    @Column(uniqueKey = true)
    public String name;

    @Column(uniqueKey = true)
    public String tag;

    @Column
    public double balance;

    @Column
    @Nullable
    public String description;

    @Column(name = "creator_uuid")
    public String creatorUuid;

    @Column(name = "creation_time")
    public long creationTime;
}
