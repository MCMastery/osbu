package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.PlayerUtility;

import java.util.Set;
import java.util.UUID;

public interface IArena {
    Set<UUID> getPlayers();
    void playerJoin(PlayerUtility player);
    void playerQuit(PlayerUtility player);

}
