document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll('.top');
    var resetButton = document.getElementById('resetButton');
    var resetButton1 = document.getElementById('resetButton1');
    var resetButton2 = document.getElementById('resetButton2');
	buttons.forEach(function (button) {
		button.addEventListener('click', function () {
			buttons.forEach(function (btn) {
				btn.classList.remove('active-button');
			});
			button.classList.add('active-button');
		});
	});
	resetButton.addEventListener('click', function () {
        buttons.forEach(function (btn) {
            btn.classList.remove('active-button');
        });
    });
    resetButton1.addEventListener('click', function () {
        buttons.forEach(function (btn) {
            btn.classList.remove('active-button');
        });
    });
    resetButton2.addEventListener('click', function () {
        buttons.forEach(function (btn) {
            btn.classList.remove('active-button');
        });
    });

    document.getElementById("add-station-button").addEventListener("click", function() {
        resetForm(); // Reset the form first
        document.getElementById("form-heading").innerText = "Station Registration";
        document.getElementById("submit-button").innerText = "Submit";
        document.getElementById("station-form").action = "/convox/stations"; // Set form action for adding a new station
        document.getElementById("station-form-container").style.display = "block";
        document.getElementById("update-station-search").style.display = "none"; // Hide the search section
        document.getElementById("delete-station-search").style.display = "none";
    });

    document.getElementById("update-station-button").addEventListener("click", function() {
        document.getElementById("update-station-search").style.display = "block";
        document.getElementById("station-form-container").style.display = "none"; // Hide the form initially
        document.getElementById("delete-station-search").style.display = "none";
    });

    document.getElementById("delete-station-button").addEventListener("click", function() {
        document.getElementById("delete-station-search").style.display = "block";
        document.getElementById("station-form-container").style.display = "none"; // Hide the form
        document.getElementById("update-station-search").style.display = "none"; // Hide the search sections
    });

    window.resetForm = function() {
        document.getElementById("station-form").reset();
        document.getElementById("station-form-container").style.display = "none";
        document.getElementById("update-station-search").style.display = "none";
        document.getElementById("delete-station-search").style.display = "none";
    };

    window.searchStation = function() {
        var searchValue = document.getElementById("search-station-id").value.toLowerCase();

        var foundStation = stations.find(station =>
            station.stationId.toLowerCase() === searchValue || station.stationName.toLowerCase() === searchValue
        );

        if (foundStation) {
            document.getElementById("station-id").value = foundStation.stationId;
            document.getElementById("station-name").value = foundStation.stationName;
            document.getElementById("active-status").value = foundStation.activeStatus;
            document.getElementById("form-heading").innerText = "Edit Station";
            document.getElementById("submit-button").innerText = "Update Station";
            document.getElementById("station-form").action = "/convox/stations/" + foundStation.id; // Set form action for update
            document.getElementById("station-form-container").style.display = "block";
        } else {
            alert("Station not found");
        }
    };

    window.deleteSearchStation = function() {
        var searchValue = document.getElementById("delete-search-station-id").value.toLowerCase();
        var foundStation = stations.find(station => station.stationId.toLowerCase() === searchValue || station.stationName.toLowerCase() === searchValue );
        if (foundStation) {
            if (confirm('Are you sure you want to delete this station with ID/Name: ' + searchValue + '?')) {
                deleteStation(foundStation.id);
            }
        }
        else {
            alert("Station not found");
        }
    };

    window.deleteStation = function(id) {
        fetch(`/convox/stations/delete/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Station deleted successfully');
                location.reload(); // Reload the page to reflect changes
            } else {
                alert('Failed to delete station');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while deleting the station');
        });
    };
});