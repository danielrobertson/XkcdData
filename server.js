const express = require("express");
const Watcher = require("feed-watcher"),
  feed = "http://lorem-rss.herokuapp.com/feed?unit=second&interval=5",// "https://xkcd.com/rss.xml",
  interval = 15; // seconds

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

// alive endpoint 
app.get("/", (req, res) => {
  res.send("Alive");
})

// // Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Xkcd-data listening on port ${PORT}`);
});
