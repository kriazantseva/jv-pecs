package core.mate.academy.service;

import core.mate.academy.model.Bulldozer;
import core.mate.academy.model.Excavator;
import core.mate.academy.model.Machine;
import core.mate.academy.model.Truck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Your implementation of MachineService.
 */
public class MachineServiceImpl<T extends Machine> implements MachineService<T> {
    private final MachineProducer<T> truckProducer;
    private final MachineProducer<T> bulldozerProducer;
    private final MachineProducer<T> excavatorProducer;

    public MachineServiceImpl() {
        this.truckProducer = (MachineProducer<T>) new TruckProducer();
        this.bulldozerProducer = (MachineProducer<T>) new BulldozerProducer();
        this.excavatorProducer = (MachineProducer<T>) new ExcavatorProducer();
    }

    @Override
    public List<Machine> getAll(Class<? extends Machine> type) {
        List<? extends Machine> machines;
        machines = type == Bulldozer.class ? bulldozerProducer.get()
                : type == Excavator.class ? excavatorProducer.get()
                : type == Truck.class ? truckProducer.get()
                : null;
        return machines != null ? new ArrayList<>(machines) : Collections.emptyList();
    }

    @Override
    public void fill(List<? super T> machines, T value) {
        for (int i = 0; i < machines.size(); i++) {
            machines.set(i, value);
        }
    }

    @Override
    public void startWorking(List<? extends T> machines) {
        for (T machine : machines) {
            machine.doWork();
        }
    }
}
