package utils;

import java.util.List;

public class POJOUtilityCreateReuest {
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<City> getCity() {
		return City;
	}

	public void setCity(List<City> city) {
		City = city;
	}

	private String name;
    private List<String> languages;
    private List<City> City;
  
   // private List<City> city;
    
    // Getters and setters (generated automatically or manually)

    public static class City {
    	private String Name;
        private String Temperature;

    	
        public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getTemperature() {
			return Temperature;
		}
		public void setTemperature(String temperature) {
			Temperature = temperature;
		}
		
        // Getters and setters (generated automatically or manually)
    }
}
