$(document).ready(function () {
    $('#getAllEvents').click(function () {
        $.ajax({
            url: 'http://localhost:8080/App1_Web_exploded/event', // Adjust based on your app context
            method: 'GET',
            success: function (response) {
                console.log('✅ Events fetched from the database:');
                console.log(response); // Logs the array of event objects

                // Optional: loop through and log each event individually
                response.forEach(event => {
                    console.log(`ID: ${event.eid}`);
                    console.log(`Name: ${event.ename}`);
                    console.log(`Description: ${event.edescription}`);
                    console.log(`Date: ${event.edate}`);
                    console.log(`Place: ${event.eplace}`);
                    console.log('--------------------------');
                });
            },
            error: function (xhr, status, error) {
                console.error('❌ Failed to fetch events:', error);
            }
        });
    });
});
