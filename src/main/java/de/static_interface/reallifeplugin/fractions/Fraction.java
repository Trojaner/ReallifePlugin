/*
 * Copyright (c) 2014 http://adventuria.eu, http://static-interface.de and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.static_interface.reallifeplugin.fractions;

import de.static_interface.reallifeplugin.VaultBridge;
import de.static_interface.sinklibrary.configuration.ConfigurationBase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Fraction
{
    final String name;
    final ConfigurationBase config;

    protected boolean isPvPAllowed;
    protected boolean isGriefingAllowed;
    protected String base;
    protected List<UUID> members;
    protected UUID leader;

    public Fraction(ConfigurationBase config, String name)
    {
        this.config = config;
        this.name = name;

        isGriefingAllowed = (boolean) getValue(FractionValues.GRIEFING_ALOWED);
        isPvPAllowed = (boolean) getValue(FractionValues.PVP_ALLOWED);
        base = (String) getValue(FractionValues.BASE);
        members = getMembersFromConfig();
        leader = (UUID) getValue(FractionValues.LEADER);
    }

    public void setLeader(UUID leader)
    {
        this.leader = leader;
        setValue(FractionValues.LEADER, leader);
        save();
    }

    public void setBase(String base)
    {
        this.base = base;
        setValue(FractionValues.BASE, base);
        save();
    }

    public String getName()
    {
        return name;
    }

    public void addMember(UUID uuid)
    {
        members.add(uuid);
        setValue(FractionValues.MEMBERS, members);
        save();
    }

    public void removeMember(UUID uuid)
    {
        members.remove(uuid);
        setValue(FractionValues.MEMBERS, members);
        save();
    }

    public void save()
    {
        config.save();
    }

    public double getMoney()
    {
        return VaultBridge.getBalance(name);
    }

    public void addBalance()
    {

    }

    public boolean isPvPAllowed()
    {
        return isPvPAllowed;
    }

    public boolean isGriefingAllowed()
    {
        return isGriefingAllowed;
    }

    public String getBase()
    {
        return base;
    }

    public List<UUID> getMembers()
    {
        return members;
    }

    public UUID getLeader()
    {
        return leader;
    }

    public List<UUID> getMembersFromConfig()
    {
        List<UUID> tmp = new ArrayList<>();

        for(String s : config.getYamlConfiguration().getStringList("Fractions." + getName() + "." + FractionValues.MEMBERS))
        {
            tmp.add(UUID.fromString(s));
        }
        return tmp;
    }

    public Object getValue(String path)
    {
        return config.get("Fractions." + getName() + "." + path);
    }

    public void setValue(String path, Object value)
    {
        config.set("Fractions." + getName() + "." + path, value);
    }
}
