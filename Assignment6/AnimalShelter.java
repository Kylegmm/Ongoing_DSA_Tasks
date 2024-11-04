package Assignment6;

import java.util.LinkedList;
import java.util.Queue;

public class AnimalShelter {
    private static int timestamp = 0; // To keep track of arrival order
    private Queue<Animal> dogs;       // Queue for dogs
    private Queue<Animal> cats;       // Queue for cats

    // Constructor initializes the queues
    public AnimalShelter() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
    }

    // Enqueue an animal (either dog or cat) into the shelter
    public void enqueue(Animal animal) {
        animal.setOrder(timestamp++); // Set order and increment timestamp

        if (animal instanceof Dog) {
            dogs.add(animal);
            System.out.println("Dog enqueued with order: " + animal.getOrder());
        } else if (animal instanceof Cat) {
            cats.add(animal);
            System.out.println("Cat enqueued with order: " + animal.getOrder());
        }
    }

    // Dequeue any animal, based on the oldest timestamp between dogs and cats
    public Animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            System.out.println("No animals available for adoption.");
            return null;
        } else if (dogs.isEmpty()) {
            return dequeueCat();
        } else if (cats.isEmpty()) {
            return dequeueDog();
        }

        // Compare timestamps to find the oldest animal
        Animal oldestDog = dogs.peek();
        Animal oldestCat = cats.peek();

        if (oldestDog.getOrder() < oldestCat.getOrder()) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    // Dequeue the oldest dog
    public Animal dequeueDog() {
        if (!dogs.isEmpty()) {
            Animal dog = dogs.poll();
            System.out.println("Adopting dog with order: " + dog.getOrder());
            return dog;
        } else {
            System.out.println("No dogs available for adoption.");
            return null;
        }
    }

    // Dequeue the oldest cat
    public Animal dequeueCat() {
        if (!cats.isEmpty()) {
            Animal cat = cats.poll();
            System.out.println("Adopting cat with order: " + cat.getOrder());
            return cat;
        } else {
            System.out.println("No cats available for adoption.");
            return null;
        }
    }

    // Animal superclass with order and type
    public static abstract class Animal {
        private int order;  // Timestamp of arrival

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }

    // Dog subclass extending Animal
    public static class Dog extends Animal {
        @Override
        public String toString() {
            return "Dog (Order: " + getOrder() + ")";
        }
    }

    // Cat subclass extending Animal
    public static class Cat extends Animal {
        @Override
        public String toString() {
            return "Cat (Order: " + getOrder() + ")";
        }
    }

    // Main method to test the AnimalShelter functionality
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();
        
        // Enqueue some dogs and cats
        shelter.enqueue(new Dog());
        shelter.enqueue(new Cat());
        shelter.enqueue(new Dog());
        shelter.enqueue(new Cat());

        // Test dequeue operations
        shelter.dequeueAny(); // Should adopt the oldest animal
        shelter.dequeueDog(); // Should adopt the oldest dog
        shelter.dequeueCat(); // Should adopt the oldest cat

        // Add more animals
        shelter.enqueue(new Dog());
        shelter.enqueue(new Cat());
        
        // More dequeue tests
        shelter.dequeueAny();
        shelter.dequeueAny();
    }
}

