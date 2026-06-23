package cc.maicra999.vaccinium.event.impl;

import cc.maicra999.vaccinium.Vaccinium;
import cc.maicra999.vaccinium.VacciniumConfig;
import cc.maicra999.vaccinium.event.EventListener;
import cc.maicra999.vaccinium.item.AttributeType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("UnstableApiUsage")
public class InventoryEventListener extends EventListener {

    public InventoryEventListener(Vaccinium vaccinium) {
        super(vaccinium);
    }

    @EventHandler
    public void onInventorySlotChange(PlayerInventorySlotChangeEvent event) {
        if (isItemDisallowed(event.getNewItemStack())) {
            event.getPlayer().getInventory().setItem(event.getSlot(), null);
        }
    }

    private boolean isItemDisallowed(ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return false;
        }

        VacciniumConfig config = vaccinium.config();

        // Check illegal item attributes
        if (item.hasData(DataComponentTypes.ATTRIBUTE_MODIFIERS)) {
            ItemAttributeModifiers attributeData = item.getData(DataComponentTypes.ATTRIBUTE_MODIFIERS);
            for (ItemAttributeModifiers.Entry entry : attributeData.modifiers()) {
                AttributeType type = AttributeType.fromBukkit(entry.attribute());
                if (type == null) {
                    continue;
                }
                if (config.disallowedItemAttributes.contains(type)) {
                    return true;
                }
            }
        }

        // Check illegal enchantments
        if (item.hasData(DataComponentTypes.ENCHANTMENTS)) {
            ItemEnchantments enchData = item.getData(DataComponentTypes.ENCHANTMENTS);
            for (Map.Entry<Enchantment, Integer> entry : enchData.enchantments().entrySet()) {
                int level = entry.getValue();

                if (level > config.maxEnchantmentLevel) {
                    return true;
                }
            }
        }

        return false;
    }
}
