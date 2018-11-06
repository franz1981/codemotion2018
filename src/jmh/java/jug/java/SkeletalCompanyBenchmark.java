package jug.java;

public class SkeletalCompanyBenchmark {


    protected jug.kotlin.Company kotlinCompany;
    protected jug.java.Company javaCompany;

    public void init() {
        kotlinCompany = new jug.kotlin.Company("jug");
        javaCompany = new jug.java.Company("jug");
    }


}
