package pl.edu.agh.handler.portion;

import pl.edu.agh.ConfigFileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UnfairPortionHandler implements PortionHandler {
    private final Random rand = new Random();
    private List<Integer> portions = null;
    private final int maxPortion = Integer.parseInt(Objects.requireNonNull(ConfigFileParser.BUFF_SIZE.getValue())) / 2;

    protected void initPortions() {
        portions = new ArrayList<>();
        for (int i = 1; i <= Math.log(maxPortion) / Math.log(2); i *= 2) {
            portions.add(i);
            for (int j = 0; j < 50 - i; j++) {
                portions.add(i);
            }
        }
    }

    @Override
    public int getPortion() {
        if (portions == null) {
            initPortions();
        }
        int index = rand.nextInt(portions.size());
        return portions.get(index);
    }
}
