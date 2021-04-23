package com.xtransformers.chapter10.optional;

import java.util.Optional;

public class Demo {

    // 1. 创建 Optional 对象
    public void test1() {
        // 声明一个空的 Optional
        Optional<Car> car = Optional.empty();

        // 根据一个非空值创建 Optional
        Optional<Car> car1 = Optional.of(new Car());

        // 可接受 null 的 Optional
        Optional<Car> car2 = Optional.ofNullable(null);
    }

    // 2. 使用 map 从 Optional 中提取和转换值
    public void test2() {
        Insurance insurance = null;
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
    }

    // 3. 使用 flatMap 链接 Optional
    public void test3() {
        Person person = new Person();
        Optional<Person> optPerson = Optional.ofNullable(person);
//        optPerson.map(Person::getCar) // Optional<Optional<Car>>
//                .map(Car::getInsurance) // 编译报错
//                .map(Insurance::getName)
        // flatMap(Function<? super T, Optional<U>> mapper)
        // map(Function<? super T, ? extends U> mapper)
        Optional<String> optName = optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName);
        String name = optName.orElse("unknown");
    }

    // Optional 无法序列化
    // 替代方案
    class SerialiableDemo {
        private Car car;

        public Optional<Car> getCarAsOptional() {
            return Optional.ofNullable(car);
        }
    }

    // Optional 解引用
    public void test4() {
        Optional<Person> optPerson = Optional.ofNullable(new Person());
        Person person = optPerson.get();
        Person person1 = optPerson.orElse(new Person());
        Person person2 = optPerson.orElseGet(Person::new);
        Person person3 = optPerson.orElseThrow(() -> new NullPointerException(""));
        optPerson.ifPresent(System.out::println);
    }

    // 两个 Optional 组合

    public Insurance findCheapestInsurance(Person person, Car car) {
        return car.getInsurance().get();
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.ofNullable(findCheapestInsurance(person.get(), car.get()));
        }
        return Optional.empty();
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    // 使用 filter 过滤
    public void test5() {
        Insurance insurance = new Insurance();
        if (insurance != null && "CambridgeInsurance".equals(insurance.getName())) {
            System.out.println("OK");
        }

        Optional.ofNullable(insurance)
                .filter(i -> "CambridgeInsurance".equals(i.getName()))
                .ifPresent(x -> System.out.println("OK"));
    }


    public String getInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() > minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }

}
