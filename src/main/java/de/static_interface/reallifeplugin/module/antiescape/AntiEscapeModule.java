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

package de.static_interface.reallifeplugin.module.antiescape;

import de.static_interface.reallifeplugin.ReallifeMain;
import de.static_interface.reallifeplugin.module.Module;
import org.bukkit.plugin.Plugin;

public class AntiEscapeModule extends Module {

    public static final String NAME = "AntiEscape";

    public AntiEscapeModule(Plugin plugin) {
        super(plugin, ReallifeMain.getInstance().getSettings(), null, NAME, true);
    }

    @Override
    protected void onEnable() {
        addDefaultValue("AutoBanTime", 5);
        registerListener(new AntiEscapeListener(this));
    }

    public int getAntiEscapeBanTime() {
        return Integer.valueOf(String.valueOf(getValue("AutoBanTime")));
    }
}
