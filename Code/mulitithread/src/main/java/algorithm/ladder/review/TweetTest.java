package algorithm.ladder.review;

import java.util.*;

public class TweetTest {

    static class MiniTwitter {
        public static class Tweet {
            public int id;
            public int user_id;
            public String text;
//       public static Tweet create(int user_id, String tweet_text) {
            // This will create a new tweet object,
            // and auto fill id
//       }
        }

        class Node {
            private int time;
            private Tweet tweet;

            public Node(int time, Tweet tweet) {
                this.time = time;
                this.tweet = tweet;
            }
        }

        class SortSeed implements Comparator<Node> {

            public int compare(Node n1, Node n2) {
                return n2.time - n1.time;
            }
        }

        int increasetime = 0;

        //from_user_id , to_user_id
        Map<Integer, Set<Integer>> friendsRelation;
        //user_id , tweet context
        Map<Integer, List<Node>> userTweet;

        public MiniTwitter() {
            // do intialization if necessary
            friendsRelation = new HashMap<>();
            userTweet = new HashMap<>();
        }

        /*
         * @param user_id: An integer
         * @param tweet_text: a string
         * @return: a tweet
         */
//        public Tweet postTweet(int user_id, String tweet_text) {
        // write your code here
//            Tweet newTweet =  Tweet.create(user_id,tweet_text);
//            List<Node> tweetQueue = userTweet.getOrDefault(user_id,new ArrayList<>());
//            increasetime++;
//            tweetQueue.add(new Node(increasetime,newTweet));
//            Collections.sort(tweetQueue,new SortSeed());
//            userTweet.put(user_id, tweetQueue);
//            return  newTweet;
//        }

        /*
         * @param user_id: An integer
         * @return: a list of 10 new feeds recently and sort by timeline
         */
        public List<Tweet> getNewsFeed(int user_id) {
            // merge followed user Tweet
            Set<Integer> followedUser = friendsRelation.get(user_id);
            List<Tweet> ans = new ArrayList<>();
            for (Integer userId : followedUser) {
                List<Node> userNodes = userTweet.get(userId);
                for (Node con : userNodes) {
                    ans.add(con.tweet);
                }
            }
//            Collections.sort(ans,new SortSeed());
            return ans;
        }

        /*
         * @param user_id: An integer
         * @return: a list of 10 new posts recently and sort by timeline
         */
        public List<Tweet> getTimeline(int user_id) {
            //get himself tweet
            List<Tweet> ans = new ArrayList<>();
            List<Node> userNodes = userTweet.get(user_id);
            for (Node con : userNodes) {
                ans.add(con.tweet);
            }
//            Collections.sort(ans,new SortSeed());
            return ans;
        }

        /*
         * @param from_user_id: An integer
         * @param to_user_id: An integer
         * @return: nothing
         */
        public void follow(int from_user_id, int to_user_id) {
            // write your code here
            Set<Integer> toUserSet = friendsRelation.getOrDefault(from_user_id, new HashSet<>());
//            toUserSet.add(to_user_id);
//            friendsRelation.put(from_user_id,toUserSet);
        }

        /*
         * @param from_user_id: An integer
         * @param to_user_id: An integer
         * @return: nothing
         */
        public void unfollow(int from_user_id, int to_user_id) {
            // write your code here
            Set<Integer> toUserSet = friendsRelation.get(from_user_id);
            if (toUserSet == null || toUserSet.isEmpty()) {
                return;
            }
            toUserSet.remove(to_user_id);
        }
    }


}
