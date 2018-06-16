const Watcher = require("feed-watcher"),
  feed = "http://lorem-rss.herokuapp.com/feed?unit=second&interval=5",
  //feed = "https://xkcd.com/rss.xml",
  interval = 15; // seconds

const watcher = new Watcher(feed, interval);

// check for new entries every interval of time
watcher.on("new entries", entries => {
  entries.forEach(entry => {
    console.log(entry);
  });
});

// start watching the feed.
watcher.start();
