package com.diabettracker.dao;

import java.util.List;

import com.diabettracker.model.Footsteps;

public interface IFootstepsDAO {

	List<Footsteps> getFootstepsSamples(String date, String granularity);

	void save(Footsteps footsteps);

}
