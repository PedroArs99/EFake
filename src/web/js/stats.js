//Canvas node on DOM
var ctx = document.getElementById('myChart').getContext('2d');
let myChart; //Variable to store charts


//Function to draw Data
function newChart(data, ctx, type) {
    return new Chart(ctx, {
        type: type,
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
    if (myChart !== undefined) {
        myChart.destroy();
    }

    monthlyPanel.classList.add('d-none');

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
                myChart = newChart(data, ctx, 'bar');
            });
});

//Today Stats
const todayStatsButton = document.querySelector('#today-stats');
todayStatsButton.addEventListener('click', (e) => {
    if (myChart !== undefined) {
        myChart.destroy();
    }

    monthlyPanel.classList.add('d-none');

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
                myChart = newChart(data, ctx, 'bar');
            });
});


//Monthly Stats - Default: user
const monthlyStatsButton = document.querySelector('#monthly-stats');

const monthlyPanel = document.querySelector('#monthly-panel');
const usersDatasetButton = document.querySelector('#users-dataset');
const productsDatasetButton = document.querySelector('#products-dataset');
const ratingsDatasetButton = document.querySelector('#ratings-dataset');

monthlyStatsButton.addEventListener('click', (e) => {
    usersDatasetButton.dispatchEvent(new Event('click'));
});

usersDatasetButton.addEventListener('click', (e) => {
    if (myChart !== undefined) {
        myChart.destroy();
    }

    monthlyPanel.classList.remove('d-none');

    fetch("/efake/MonthlyStats?name=user")
            .then(response => response.json())
            .then(stats => {
                let data = {
                    labels: Object.keys(stats.userCount),
                    datasets: [
                        {
                            label: '# of Users Logged In',
                            data: Object.values(stats.userCount),
                            backgroundColor: ['rgba(255, 99, 132, 0.2)'],
                            borderColor: ['rgba(255, 99, 132, 1)'],
                            borderWidth: 1
                        },
                    ]
                }
                myChart = newChart(data, ctx, 'line');
                myChart.options.legend.display = true;
                myChart.update();
            });
});

productsDatasetButton.addEventListener('click', (e) => {
    if (myChart !== undefined) {
        myChart.destroy();
    }

    monthlyPanel.classList.remove('d-none');

    fetch("/efake/MonthlyStats?name=product")
            .then(response => response.json())
            .then(stats => {
                let data = {
                    labels: Object.keys(stats.productCount),
                    datasets: [
                        {
                            label: '# of new Products',
                            data: Object.values(stats.productCount),
                            backgroundColor: ['rgba(54, 162, 235, 0.2)'],
                            borderColor: ['rgba(54, 162, 235, 1)'],
                            borderWidth: 1
                        }
                    ]
                }
                myChart = newChart(data, ctx, 'line');
                myChart.options.legend.display = true;
                myChart.update();
            });
});

ratingsDatasetButton.addEventListener('click', (e) => {
    if (myChart !== undefined) {
        myChart.destroy();
    }

    monthlyPanel.classList.remove('d-none');

    fetch("/efake/MonthlyStats?name=rating")
            .then(response => response.json())
            .then(stats => {
                let data = {
                    labels: Object.keys(stats.ratingCount),
                    datasets: [
                        {
                            label: '# of new Ratings',
                            data: Object.values(stats.ratingCount),
                            backgroundColor: ['rgba(255, 206, 86, 0.2)'],
                            borderColor: ['rgba(255, 206, 86, 1)'],
                            borderWidth: 1
                        }
                    ]
                }
                myChart = newChart(data, ctx, 'line');
                myChart.options.legend.display = true;
                myChart.update();
            });
});



//On starting the page show global Stats
globalButton.dispatchEvent(new Event('click'));

