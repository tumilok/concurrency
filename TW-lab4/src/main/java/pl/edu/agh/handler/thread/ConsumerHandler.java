package pl.edu.agh.handler.thread;

import pl.edu.agh.buffer.Storage;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.thread.Consumer;

public class ConsumerHandler extends WorkerHandler {
    public ConsumerHandler(Storage storage, PortionHandler portionHandler, int workerNumber) {
        super(storage, portionHandler, workerNumber);
    }

    @Override
    public void createAndRunWorkers() {
        for (int i = 0; i < workerNumber; i++) {
            workers.add(new Consumer(storage, portionHandler.getPortion()));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }
}
