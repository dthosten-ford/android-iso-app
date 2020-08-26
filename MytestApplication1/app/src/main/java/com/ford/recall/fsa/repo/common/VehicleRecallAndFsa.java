package com.ford.recall.fsa.repo.common;

import java.util.Collection;

public interface VehicleRecallAndFsa{

    Collection<Object> getRecallList();

    Collection<Object> getFsaList();
}
