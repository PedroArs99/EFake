//Canvas node on DOM
var ctx = document.getElementById('myChart').getContext('2d');
let myChart; //Variable to store charts

//Function to draw Data
function newChart(data, ctx) {
    return new Chart(ctx, {
        type: 'bar',
        data,
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            },
            legend: {display: false}
        }
    });
}


//Diferent stats showed
//Global Stats
const globalButton = document.querySelector('#global-stats');
globalButton.addEventListener('click', (e) => {
    if(myChart !== undefined){
        myChart.clear();
    }
   
    fetch("/efake/BasicStats")
            .then(response => response.json())
            .then(stats => {
                let data = {
                    labels: ["Number of Users", "Number of Products", "Number of Ratings"],
                    datasets: [
                        {
                            label: '# of Votes',
                            data: [stats.userCount, stats.productCount, stats.ratingCount],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        },
                    ]
                }
                myChart = newChart(data, ctx);
            });
});

//Today Stats
const todayStatsButton = document.querySelector('#today-stats');
todayStatsButton.addEventListener('click', (e) => {
    if(myChart !== undefined){
        myChart.clear();
    }
    fetch("/efake/TodayStats")
            .then(response => response.json())
            .then(stats => {
                let data = {
                    labels: ["Number of Users logged in today", "New Products created Today", "New Ratings maded Today"],
                    datasets: [
                        {
                            label: '# of Votes',
                            data: [stats.userCount, stats.productCount, stats.ratingCount],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        },
                    ]
                }
                myChart = newChart(data, ctx);
            });
});



//On starting the page show global Stats
globalButton.dispatchEvent(new Event('click'));




