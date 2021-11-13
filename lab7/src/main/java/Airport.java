import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;
import java.util.*;

public class Airport {

    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();

        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }

        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();

        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }

        return militaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();

        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }

        return experimentalPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);

        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }

        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();

        for (MilitaryPlane militaryPlane : militaryPlanes) {
            if (militaryPlane.getMilitaryType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(militaryPlane);
            }
        }

        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();

        for (MilitaryPlane militaryPlane : militaryPlanes) {
            if (militaryPlane.getMilitaryType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(militaryPlane);
            }
        }

        return bomberMilitaryPlanes;
    }

    public Airport sortByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
        return this;
    }

    private void print(List<? extends Plane> planes) {
        for (Plane plane : planes)
        {
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
