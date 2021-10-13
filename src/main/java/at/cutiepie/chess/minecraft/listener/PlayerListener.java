package at.cutiepie.chess.minecraft.listener;

import at.cutiepie.chess.minecraft.MCPlayerInput;
import at.cutiepie.chess.minecraft.model.MinecraftBoard;
import at.cutiepie.chess.model.Coordinate;
import at.cutiepie.chess.model.Figure;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;
import java.util.stream.Stream;

public class PlayerListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            handleInteract(e.getPlayer(), e.getClickedBlock().getLocation());
        } else if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            Block target = e.getPlayer().getTargetBlock(50);
            if (target != null) {
                handleInteract(e.getPlayer(), target.getLocation());
            }
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e) {
        handleInteract(e.getPlayer(), e.getRightClicked().getLocation());
    }

    private void handleInteract(Player player, Location l) {
        //find proper field
        Optional<MinecraftBoard> mcboardOpt = Optional.empty();

        mcboardOpt.ifPresent(mcboard -> {
            //find interact coord
            Optional<Coordinate> coord = mcboard.getCoordinate(l);
            if (coord.isEmpty()) {
                return;
            }

            //find figure
            Optional<Figure> figure = mcboard.getFigureAt(coord.get());

            //find input
            Optional<MCPlayerInput> inputOpt = Stream.of(mcboard.getInputBlack(), mcboard.getInputBlack())
                    .filter(i -> i instanceof MCPlayerInput)
                    .map(i -> (MCPlayerInput) i)
                    .filter(i -> i.getUuid().equals(player.getUniqueId()))
                    .findFirst();
            inputOpt.ifPresent(input -> handleFigureInteract(mcboard, input, coord.get(), figure));
        });
    }

    private void handleFigureInteract(MinecraftBoard board, MCPlayerInput input, Coordinate coord, Optional<Figure> f) {
        if (f.isPresent()) {
            if (input.getSelectedFigure() == null) {
                input.setSelectedFigure(f.get());
            } else if (input.getSelectedFigure() == f.get()) {
                input.setSelectedFigure(null);
            }
        } else {
            if (input.getSelectedFigure() != null) {
                input.moveFigureTo(coord);
            }
        }
    }
}
