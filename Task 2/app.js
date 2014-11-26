/*
* Sylvia Barnai
* Brant Unger
* Lab 5
* Task 2
*/

/* Controller used to call and manage functions for Weather App in AngularJS */

function myCityController($scope, $http) {
	// Create a JSON array of 3 predefined cities
	$scope.cities = [{name: 'Budapest'},{name: 'London'},{name: 'Phoenix'}];
	
	
	/*
	* Calculates the average temperature of the cities
	*/
	$scope.getAverageTemperature = function() {
	var averageTemperature = 0;
      
      for (var i=0; i < $scope.cities.length; i++) {
        averageTemperature = averageTemperature + $scope.cities[i].main.temp-273.15;
      }
      
	  var averageTemperatureValue = averageTemperature / $scope.cities.length;
      return averageTemperatureValue;
	};
	
	
	/*
	* Calculates the average humidity of the cities
	*/
	$scope.getAverageHumidity = function() {
	
	var averageHumidity=0;
      for (var i=0; i < $scope.cities.length; i++) {
        averageHumidity = averageHumidity + $scope.cities[i].main.humidity;
      }
      var averageHumidityValue = averageHumidity / $scope.cities.length;
      return averageHumidityValue;
	};
	
	/*
	* Determines which city has the highest temperature
	*/
	$scope.getHottestCity= function() {
	
	  var hottestCity;
      for (var i=0; i < $scope.cities.length; i++) {
      if (hottestCity == undefined || $scope.cities[i].main.temp > $scope.cities[hottestCity].main.temp) {
				hottestCity = i;
			}
	}
      var cityWithHighestTemp = $scope.cities[hottestCity].name
      return cityWithHighestTemp;
    };
	
	
	/*
	* Determines which city has the highest humidity
	*/
	$scope.getHighestHumidity = function() {
	
	  var cityWithHighestHumidity;
      
      for (var i=0; i < $scope.cities.length; i++) {
        if (cityWithHighestHumidity == undefined || cityWithHighestHumidity > $scope.cities[i].main.humidity) {
          cityWithHighestHumidity = i;
        }
      }
      
	  var cityHighestHumidity = $scope.cities[cityWithHighestHumidity].name;
      return cityHighestHumidity;
    };
	
	
	/*
	* Compares all of a location's data and sees which one has the lowest total value (generally, the lower the values are the better)
	*/
	$scope.getNicestWeather = function() {
	
	 var lowestTotal;
	 var nicestWeather;
     
      for (var i=0; i < $scope.cities.length; i++) {
        var totalData = $scope.cities[i].main.temp +
                   $scope.cities[i].main.wind +
                   $scope.cities[i].main.humidity +
                   $scope.cities[i].main.clouds;
        
        if (lowestTotal == undefined || totalData < lowestTotal) {
          lowestTotal = totalData;
          nicestWeather = i;
        }
      }
      
      return $scope.cities[nicestWeather].name;
	};
	
	/*
	* Compares all of a location's data and sees which one has the highest total value (most extreme values=worst weather)
	*/
	$scope.getWorstWeather = function() {
	 var highestTotal;
	 var worstWeather;
     
      for (var i=0; i < $scope.cities.length; i++) {
        var totalData = $scope.cities[i].main.temp +
                   $scope.cities[i].main.wind +
                   $scope.cities[i].main.humidity +
                   $scope.cities[i].main.clouds;
        
        if (highestTotal == undefined || totalData > highestTotal) {
          highTotal = totalData;
          worstWeather = i;
        }
      }
      
      return $scope.cities[worstWeather].name;
	};
	  
	  /*
	  * Dynamically updates the city info by using an AJAX call using the $http module
	  */

	$scope.getCityInfo = function(locationIndex) {
		$http.get('http://api.openweathermap.org/data/2.5/weather?q='+$scope.cities[locationIndex].name).success(function(response){
			$scope.cities[locationIndex] = response;
			
		});
		
		
	}
	
	for(var i = 0; i < $scope.cities.length; i++){
		$scope.getCityInfo(i);
	}
}

	