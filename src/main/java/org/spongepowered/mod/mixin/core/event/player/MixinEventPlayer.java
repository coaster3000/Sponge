/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod.mixin.core.event.player;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.entity.player.PlayerEvent;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.mod.interfaces.IMixinEvent;

@NonnullByDefault
@Mixin(value = net.minecraftforge.event.entity.player.PlayerEvent.class, remap = false)
public abstract class MixinEventPlayer extends LivingEvent implements PlayerEvent {

    @Shadow public EntityPlayer entityPlayer;

    public MixinEventPlayer(EntityLivingBase entity) {
        super(entity);
    }

    @Override
    public Player getEntity() {
        return (Player) this.entityPlayer;
    }

    @Override
    public Player getUser() {
        return (Player) this.entityPlayer;
    }

    private static net.minecraftforge.event.entity.player.PlayerEvent fromSpongeEvent(PlayerEvent spongeEvent) {
        net.minecraftforge.event.entity.player.PlayerEvent event = new net.minecraftforge.event.entity.player.PlayerEvent((EntityPlayer) spongeEvent.getEntity());
        ((IMixinEvent) event).setSpongeEvent(spongeEvent);
        return event;
    }
}
