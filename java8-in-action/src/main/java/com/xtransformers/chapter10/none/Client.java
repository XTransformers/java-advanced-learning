package com.xtransformers.chapter10.none;

public class Client {
    public static void main(String[] args) {

    }

    public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    // 采用防御式检查减少 NullPointerException
    public String getCarInsuranceName1(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "unknow";
    }

    // 卫语句
    public String getCarInsuranceName2(Person person) {
        if (person == null) {
            return "unknow";
        }

        Car car = person.getCar();
        if (car == null) {
            return "unknow";
        }

        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "unknow";
        }

        return insurance.getName();
    }
}
