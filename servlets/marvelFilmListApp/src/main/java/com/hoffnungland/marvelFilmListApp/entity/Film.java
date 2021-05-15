package com.hoffnungland.marvelFilmListApp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
@Entity
public class Film {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	
	private Integer season;
	
	private Integer episode;
	
	private String episodeName;
	
	private Date releaseDate;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getSeason() {
		return season;
	}

	public Integer getEpisode() {
		return episode;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public void setEpisode(Integer episode) {
		this.episode = episode;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
}
