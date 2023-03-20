package qaa.be.petfinderapi.requests;

import qaa.be.core.exceptions.ConversionJsonToModelException;

public interface APIContractPet<T> {

    T createItem(T item) throws ConversionJsonToModelException;

    T createItemWithoutBody() throws ConversionJsonToModelException;

}
