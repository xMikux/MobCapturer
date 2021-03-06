package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class CatAdapter extends AbstractTameableAdapter<Cat> {

    public CatAdapter() {
        super(Cat.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "品種: " + ChatColor.WHITE + ChatUtils.humanize(json.get("catType").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "項圈顏色: " + ChatColor.WHITE + ChatUtils.humanize(json.get("collarColor").getAsString()));
            lore.add(ChatColor.GRAY + "坐著: " + ChatColor.WHITE + json.get("sitting").getAsBoolean());
        }

        return lore;
    }

    @Override
    public void apply(Cat entity, JsonObject json) {
        super.apply(entity, json);

        entity.setCatType(Type.valueOf(json.get("catType").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
    }

    @Override
    public JsonObject saveData(Cat entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("catType", entity.getCatType().name());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());

        return json;
    }

}
