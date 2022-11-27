package agh.ics.oop;

import agh.ics.oop.gui.IMapRefreshObserver;

import java.util.ArrayList;

public class ThreadedSimulationEngine implements IEngine, Runnable {

    private final IWorldMap map;
    private MoveDirection[] moves;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<IMapRefreshObserver> mapRefreshObservers = new ArrayList<>();

    public final int moveDelay;

    public ThreadedSimulationEngine(IWorldMap map, Vector2d[] initialPositions, int moveDelay) {
        this.map = map;
        this.moveDelay = moveDelay;

        for (Vector2d position: initialPositions) {
            animals.add(new Animal(this.map, position));
        }

        for (Animal animal: animals) {
            this.map.place(animal);
        }
    }

    public ThreadedSimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPosiitons, int moveDelay) {
        this(map, initialPosiitons, moveDelay);
        this.setMoves(moves);
    }

    void mapRefresh() {
        for (IMapRefreshObserver observer: this.mapRefreshObservers) {
            observer.refresh();
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < this.moves.length; i++) {
                this.animals.get(i % this.animals.size()).move(this.moves[i]);

                this.mapRefresh();
                Thread.sleep(moveDelay);
            }
        }
        catch (InterruptedException e) {
            System.err.println("Interuption while waiting for the animal move!");
        }
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }
    public void addObserver(IMapRefreshObserver observer) {
        this.mapRefreshObservers.add(observer);
    }

    public void removeObserver(IMapRefreshObserver observer) {
        this.mapRefreshObservers.remove(observer);
    }
}
