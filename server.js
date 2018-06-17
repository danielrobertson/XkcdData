const express = require("express");
const Watcher = require("feed-watcher"),
  feed = "http://lorem-rss.herokuapp.com/feed?unit=second&interval=5",
  //feed = "https://xkcd.com/rss.xml",
  interval = 15; // seconds

const app = express();

// check for new entries every interval of time
const watcher = new Watcher(feed, interval);
watcher.on("new entries", entries => {
  entries.forEach(entry => {
    console.log(entry);
  });
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

// search endpoint for UI
app.get("/search", (req, res) => {
  const keyword = req.query.q;
  console.log("search with keyword - %s", keyword);

  // todo dynamically return best match comic
  const comic = {
    alt:
      "If you get an 11/100 on a CS test, but you claim it should be counted as a 'C', they'll probably decide you deserve the upgrade.",
    day: "19",
    img: "https://imgs.xkcd.com/comics/1_to_10.png",
    link: "",
    month: "9",
    news: "",
    num: 953,
    safe_title: "1 to 10",
    title: "1 to 10",
    transcript:
      "[[Two people are talking.]] Person 1: On a scale of 1 to 10, how likely is it that this question is using Binary? Person 2: ...4? Person 1: What's a 4? {{Title text: If you get an 11 100 on a CS test, but you claim it should be counted as a 'C', they'll probably decide you deserve the upgrade.}}",
    year: "2011"
  };
  res
    .status(200)
    .send(comic)
    .end();
});

app.get("/", (req, res) => {
  res
    .status(200)
    .send("Hello, world!")
    .end();
});

// Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`App listening on port ${PORT}`);
  console.log("Press Ctrl+C to quit.");
});
