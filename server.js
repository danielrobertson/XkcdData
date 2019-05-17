const express = require("express");
const Watcher = require("feed-watcher"),
  feed = "https://xkcd.com/rss.xml",
  interval = 15; // seconds

//feed = "http://lorem-rss.herokuapp.com/feed?unit=second&interval=5",
const app = express();

// update elastic search with new comic 
const updateElastic = comic => {
  console.log(comic);
}

// check for new entries every interval of time
const watcher = new Watcher(feed, interval);
watcher.on("new entries", entries => {
  entries.forEach(updateElastic);
});

// start watching the feed.
watcher.start();

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept"
  );
  next();
});

// Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`App listening on port ${PORT}`);
  console.log("Press Ctrl+C to quit.");
});
