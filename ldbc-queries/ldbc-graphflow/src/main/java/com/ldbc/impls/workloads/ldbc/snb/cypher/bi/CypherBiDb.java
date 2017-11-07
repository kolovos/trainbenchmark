package com.ldbc.impls.workloads.ldbc.snb.cypher.bi;

import com.ldbc.driver.DbException;
import com.ldbc.driver.control.LoggingService;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery4PopularCountryTopics;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery4PopularCountryTopicsResult;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery7AuthoritativeUsers;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery7AuthoritativeUsersResult;
import com.ldbc.impls.workloads.ldbc.snb.cypher.CypherDb;
import com.ldbc.impls.workloads.ldbc.snb.cypher.CypherDriverConnectionStore;
import com.ldbc.impls.workloads.ldbc.snb.cypher.CypherListOperationHandler;
import com.ldbc.impls.workloads.ldbc.snb.cypher.CypherPoolingDbConnectionStore;
import com.ldbc.impls.workloads.ldbc.snb.util.Converter;

import java.text.ParseException;
import java.util.Map;

public class CypherBiDb extends CypherDb {

	@Override
	protected void onInit(Map<String, String> properties, LoggingService loggingService) throws DbException {
		dbs = new CypherPoolingDbConnectionStore(properties, new CypherBiQueryStore(properties.get("queryDir")));

//		registerOperationHandler(LdbcSnbBiQuery1PostingSummary.class, BiQuery1.class);
//		registerOperationHandler(LdbcSnbBiQuery2TopTags.class, BiQuery2.class);
//		registerOperationHandler(LdbcSnbBiQuery3TagEvolution.class, BiQuery3.class);
		registerOperationHandler(LdbcSnbBiQuery4PopularCountryTopics.class, BiQuery4.class);
//		registerOperationHandler(LdbcSnbBiQuery5TopCountryPosters.class, BiQuery5.class);
//		registerOperationHandler(LdbcSnbBiQuery6ActivePosters.class, BiQuery6.class);
		registerOperationHandler(LdbcSnbBiQuery7AuthoritativeUsers.class, BiQuery7.class);
//		registerOperationHandler(LdbcSnbBiQuery8RelatedTopics.class, BiQuery8.class);
//		registerOperationHandler(LdbcSnbBiQuery9RelatedForums.class, BiQuery9.class);
//		registerOperationHandler(LdbcSnbBiQuery10TagPerson.class, BiQuery10.class);
//		registerOperationHandler(LdbcSnbBiQuery11UnrelatedReplies.class, BiQuery11.class);
//		registerOperationHandler(LdbcSnbBiQuery12TrendingPosts.class, BiQuery12.class);
//		registerOperationHandler(LdbcSnbBiQuery13PopularMonthlyTags.class, BiQuery13.class);
//		registerOperationHandler(LdbcSnbBiQuery14TopThreadInitiators.class, BiQuery14.class);
//		registerOperationHandler(LdbcSnbBiQuery15SocialNormals.class, BiQuery15.class);
//		registerOperationHandler(LdbcSnbBiQuery16ExpertsInSocialCircle.class, BiQuery16.class);
//		registerOperationHandler(LdbcSnbBiQuery17FriendshipTriangles.class, BiQuery17.class);
//		registerOperationHandler(LdbcSnbBiQuery18PersonPostCounts.class, BiQuery18.class);
//		registerOperationHandler(LdbcSnbBiQuery19StrangerInteraction.class, BiQuery19.class);
//		registerOperationHandler(LdbcSnbBiQuery20HighLevelTopics.class, BiQuery20.class);
//		registerOperationHandler(LdbcSnbBiQuery21Zombies.class, BiQuery21.class);
//		registerOperationHandler(LdbcSnbBiQuery22InternationalDialog.class, BiQuery22.class);
//		registerOperationHandler(LdbcSnbBiQuery23HolidayDestinations.class, BiQuery23.class);
//		registerOperationHandler(LdbcSnbBiQuery24MessagesByTopic.class, BiQuery24.class);
//		registerOperationHandler(LdbcSnbBiQuery25WeightedPaths.class, BiQuery25.class);
	}

//	public static class BiQuery1 extends CypherListOperationHandler<LdbcSnbBiQuery1PostingSummary, LdbcSnbBiQuery1PostingSummaryResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery1PostingSummary operation) {
//			return state.getQueryStore().getQuery1(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery1PostingSummaryResult convertSingleResult(Object[] tuple) {
//			int year = (int) tuple[0];
//			boolean isComment = (boolean) tuple[1];
//			int size = (int) tuple[2];
//			long count = (long) tuple[3];
//			int avgLen = (int) tuple[4];
//			int total = (int) tuple[5];
//			double pct = (double) tuple[6];
//
//			return new LdbcSnbBiQuery1PostingSummaryResult(year, isComment, size, count, avgLen, total, (float) pct);
//		}
//
//	}
//
//	public static class BiQuery2 extends CypherListOperationHandler<LdbcSnbBiQuery2TopTags, LdbcSnbBiQuery2TopTagsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery2TopTags operation) {
//			return state.getQueryStore().getQuery2(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery2TopTagsResult convertSingleResult(Object[] tuple) {
//			String country = (String) tuple[0];
//			int month = (int) tuple[1];
//			String gender = (String) tuple[2];
//			int ageGroup = (int) tuple[3];
//			String tag = (String) tuple[4];
//			int count = (int) tuple[5];
//			return new LdbcSnbBiQuery2TopTagsResult(country, month, gender, ageGroup, tag, count);
//		}
//
//	}
//
//	public static class BiQuery3 extends CypherListOperationHandler<LdbcSnbBiQuery3TagEvolution, LdbcSnbBiQuery3TagEvolutionResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery3TagEvolution operation) {
//			return state.getQueryStore().getQuery3(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery3TagEvolutionResult convertSingleResult(Object[] tuple) {
//			String tagName = (String) tuple[0];
//			int countA = (int) tuple[1];
//			int countB = (int) tuple[2];
//			int diffCount = (int) tuple[3];
//			return new LdbcSnbBiQuery3TagEvolutionResult(tagName, countA, countB, diffCount);
//		}
//
//	}

	public static class BiQuery4 extends CypherListOperationHandler<LdbcSnbBiQuery4PopularCountryTopics, LdbcSnbBiQuery4PopularCountryTopicsResult, CypherBiQueryStore> {

		@Override
		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery4PopularCountryTopics operation) {
			return state.getQueryStore().getQuery4(operation);
		}

		@Override
		public LdbcSnbBiQuery4PopularCountryTopicsResult convertSingleResult(Object[] tuple) throws ParseException {
			long forumId = (long) tuple[0];
			String title = (String) tuple[1];
			long creationDate = new Converter().convertTimestampToEpoch((String) tuple[2]);
			long moderator = (long) tuple[3];
			int count = (int) tuple[4];
			return new LdbcSnbBiQuery4PopularCountryTopicsResult(forumId, title, creationDate, moderator, count);
		}
	}

//	public static class BiQuery5 extends CypherListOperationHandler<LdbcSnbBiQuery5TopCountryPosters, LdbcSnbBiQuery5TopCountryPostersResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery5TopCountryPosters operation) {
//			return state.getQueryStore().getQuery5(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery5TopCountryPostersResult convertSingleResult(Object[] tuple) throws ParseException {
//			long personId = (long) tuple[0];
//			String firstName = (String) tuple[1];
//			String lastName = (String) tuple[2];
//			long creationDate = new Converter().convertTimestampToEpoch((String) tuple[3]);
//			int count = (int) tuple[4];
//			return new LdbcSnbBiQuery5TopCountryPostersResult(personId, firstName, lastName, creationDate, count);
//		}
//
//	}
//
//	public static class BiQuery6 extends CypherListOperationHandler<LdbcSnbBiQuery6ActivePosters, LdbcSnbBiQuery6ActivePostersResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery6ActivePosters operation) {
//			return state.getQueryStore().getQuery6(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery6ActivePostersResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			int postCount = (int) tuple[1];
//			int replyCount = (int) tuple[2];
//			int likeCount = (int) tuple[3];
//			int score = (int) tuple[4];
//			return new LdbcSnbBiQuery6ActivePostersResult(personId, postCount, replyCount, likeCount, score);
//		}
//
//	}

	public static class BiQuery7 extends CypherListOperationHandler<LdbcSnbBiQuery7AuthoritativeUsers, LdbcSnbBiQuery7AuthoritativeUsersResult, CypherBiQueryStore> {

		@Override
		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery7AuthoritativeUsers operation) {
			return state.getQueryStore().getQuery7(operation);
		}

		@Override
		public LdbcSnbBiQuery7AuthoritativeUsersResult convertSingleResult(Object[] tuple) {
			long personId = (long) tuple[0];
			int score = (int) tuple[1];
			return new LdbcSnbBiQuery7AuthoritativeUsersResult(personId, score);
		}

	}

//	public static class BiQuery8 extends CypherListOperationHandler<LdbcSnbBiQuery8RelatedTopics, LdbcSnbBiQuery8RelatedTopicsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery8RelatedTopics operation) {
//			return state.getQueryStore().getQuery8(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery8RelatedTopicsResult convertSingleResult(Object[] tuple) {
//			String tag = (String) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery8RelatedTopicsResult(tag, count);
//		}
//
//	}
//
//	public static class BiQuery9 extends CypherListOperationHandler<LdbcSnbBiQuery9RelatedForums, LdbcSnbBiQuery9RelatedForumsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery9RelatedForums operation) {
//			return state.getQueryStore().getQuery9(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery9RelatedForumsResult convertSingleResult(Object[] tuple) {
//			long forumId = (long) tuple[0];
//			int sumA = (int) tuple[1];
//			int sumB = (int) tuple[2];
//			return new LdbcSnbBiQuery9RelatedForumsResult(forumId, sumA, sumB);
//		}
//
//	}
//
//	public static class BiQuery10 extends CypherListOperationHandler<LdbcSnbBiQuery10TagPerson, LdbcSnbBiQuery10TagPersonResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery10TagPerson operation) {
//			return state.getQueryStore().getQuery10(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery10TagPersonResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			int score = (int) tuple[1];
//			int friendsScore = (int) tuple[2];
//			return new LdbcSnbBiQuery10TagPersonResult(personId, score, friendsScore);
//		}
//
//	}
//
//	public static class BiQuery11 extends CypherListOperationHandler<LdbcSnbBiQuery11UnrelatedReplies, LdbcSnbBiQuery11UnrelatedRepliesResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery11UnrelatedReplies operation) {
//			return state.getQueryStore().getQuery11(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery11UnrelatedRepliesResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			String tagName = (String) tuple[1];
//			int countLikes = (int) tuple[2];
//			int countReplies = (int) tuple[3];
//			return new LdbcSnbBiQuery11UnrelatedRepliesResult(personId, tagName, countLikes, countReplies);
//		}
//
//	}
//
//	public static class BiQuery12 extends CypherListOperationHandler<LdbcSnbBiQuery12TrendingPosts, LdbcSnbBiQuery12TrendingPostsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery12TrendingPosts operation) {
//			return state.getQueryStore().getQuery12(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery12TrendingPostsResult convertSingleResult(Object[] tuple) throws ParseException {
//			long personId = (long) tuple[0];
//			String firstName = (String) tuple[1];
//			String lastName = (String) tuple[2];
//			long creationDate = new Converter().convertTimestampToEpoch((String) tuple[3]);
//			int likeCount = (int) tuple[4];
//			return new LdbcSnbBiQuery12TrendingPostsResult(personId, firstName, lastName, creationDate, likeCount);
//		}
//	}
//
//	public static class BiQuery13 extends CypherListOperationHandler<LdbcSnbBiQuery13PopularMonthlyTags, LdbcSnbBiQuery13PopularMonthlyTagsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery13PopularMonthlyTags operation) {
//			return state.getQueryStore().getQuery13(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery13PopularMonthlyTagsResult convertSingleResult(Object[] tuple) {
//			int year = (int) tuple[0];
//			int month = (int) tuple[1];
//			final List<List<Object>> tagPopularitiesRaw = record.get(2).asList(Values.ofList());
//
//			final List<LdbcSnbBiQuery13PopularMonthlyTagsResult.TagPopularity> tagPopularities = new ArrayList<>();
//			for (List<Object> tagPopularityRaw : tagPopularitiesRaw) {
//				final String tag = (String) tagPopularityRaw.get(0);
//				final int popularity = Ints.saturatedCast((long) tagPopularityRaw.get(1));
//				tagPopularities.add(new LdbcSnbBiQuery13PopularMonthlyTagsResult.TagPopularity(tag, popularity));
//			}
//
//			return new LdbcSnbBiQuery13PopularMonthlyTagsResult(year, month, new ArrayList<>());
//		}
//	}
//
//	public static class BiQuery14 extends CypherListOperationHandler<LdbcSnbBiQuery14TopThreadInitiators, LdbcSnbBiQuery14TopThreadInitiatorsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery14TopThreadInitiators operation) {
//			return state.getQueryStore().getQuery14(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery14TopThreadInitiatorsResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			String firstName = (String) tuple[1];
//			String lastName = (String) tuple[2];
//			int count = (int) tuple[3];
//			int threadCount = (int) tuple[4];
//			return new LdbcSnbBiQuery14TopThreadInitiatorsResult(personId, firstName, lastName, count, threadCount);
//		}
//	}
//
//	public static class BiQuery15 extends CypherListOperationHandler<LdbcSnbBiQuery15SocialNormals, LdbcSnbBiQuery15SocialNormalsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery15SocialNormals operation) {
//			return state.getQueryStore().getQuery15(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery15SocialNormalsResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery15SocialNormalsResult(personId, count);
//		}
//	}
//
//	public static class BiQuery16 extends CypherListOperationHandler<LdbcSnbBiQuery16ExpertsInSocialCircle, LdbcSnbBiQuery16ExpertsInSocialCircleResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery16ExpertsInSocialCircle operation) {
//			return state.getQueryStore().getQuery16(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery16ExpertsInSocialCircleResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			String tag = (String) tuple[1];
//			int count = (int) tuple[2];
//			return new LdbcSnbBiQuery16ExpertsInSocialCircleResult(personId, tag, count);
//		}
//	}
//
//	public static class BiQuery17 extends CypherSingletonOperationHandler<LdbcSnbBiQuery17FriendshipTriangles, LdbcSnbBiQuery17FriendshipTrianglesResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery17FriendshipTriangles operation) {
//			return state.getQueryStore().getQuery17(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery17FriendshipTrianglesResult convertSingleResult(Object[] tuple) {
//			int count = (int) tuple[0];
//			return new LdbcSnbBiQuery17FriendshipTrianglesResult(count);
//		}
//	}
//
//	public static class BiQuery18 extends CypherListOperationHandler<LdbcSnbBiQuery18PersonPostCounts, LdbcSnbBiQuery18PersonPostCountsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery18PersonPostCounts operation) {
//			return state.getQueryStore().getQuery18(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery18PersonPostCountsResult convertSingleResult(Object[] tuple) {
//			int postCount = (int) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery18PersonPostCountsResult(postCount, count);
//		}
//	}
//
//
//	public static class BiQuery19 extends CypherListOperationHandler<LdbcSnbBiQuery19StrangerInteraction, LdbcSnbBiQuery19StrangerInteractionResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery19StrangerInteraction operation) {
//			return state.getQueryStore().getQuery19(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery19StrangerInteractionResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			int strangerCount = (int) tuple[1];
//			int count = (int) tuple[2];
//			return new LdbcSnbBiQuery19StrangerInteractionResult(personId, strangerCount, count);
//		}
//	}
//
//	public static class BiQuery20 extends CypherListOperationHandler<LdbcSnbBiQuery20HighLevelTopics, LdbcSnbBiQuery20HighLevelTopicsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery20HighLevelTopics operation) {
//			return state.getQueryStore().getQuery20(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery20HighLevelTopicsResult convertSingleResult(Object[] tuple) {
//			String tagClass = (String) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery20HighLevelTopicsResult(tagClass, count);
//		}
//	}
//
//	public static class BiQuery21 extends CypherListOperationHandler<LdbcSnbBiQuery21Zombies, LdbcSnbBiQuery21ZombiesResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery21Zombies operation) {
//			return state.getQueryStore().getQuery21(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery21ZombiesResult convertSingleResult(Object[] tuple) {
//			long personId = (long) tuple[0];
//			int zombieCount = (int) tuple[1];
//			int realCount = (int) tuple[2];
//			double score = (double) tuple[3];
//			return new LdbcSnbBiQuery21ZombiesResult(personId, zombieCount, realCount, score);
//		}
//	}
//
//	public static class BiQuery22 extends CypherListOperationHandler<LdbcSnbBiQuery22InternationalDialog, LdbcSnbBiQuery22InternationalDialogResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery22InternationalDialog operation) {
//			return state.getQueryStore().getQuery22(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery22InternationalDialogResult convertSingleResult(Object[] tuple) {
//			long personIdA = (long) tuple[0];
//			long personIdB = (long) tuple[1];
//			String cityName = (String) tuple[2];
//			int  score = (int) tuple[3];
//			return new LdbcSnbBiQuery22InternationalDialogResult(personIdA, personIdB, score);
//		}
//	}
//
//	public static class BiQuery23 extends CypherListOperationHandler<LdbcSnbBiQuery23HolidayDestinations, LdbcSnbBiQuery23HolidayDestinationsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery23HolidayDestinations operation) {
//			return state.getQueryStore().getQuery23(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery23HolidayDestinationsResult convertSingleResult(Object[] tuple) {
//			int count = (int) tuple[0];
//			String countryName = (String) tuple[1];
//			int month = (int) tuple[2];
//			return new LdbcSnbBiQuery23HolidayDestinationsResult(countryName, month, count);
//		}
//	}
//
//
//	public static class BiQuery24 extends CypherListOperationHandler<LdbcSnbBiQuery24MessagesByTopic, LdbcSnbBiQuery24MessagesByTopicResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery24MessagesByTopic operation) {
//			return state.getQueryStore().getQuery24(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery24MessagesByTopicResult convertSingleResult(Object[] tuple) {
//			int messageCount = (int) tuple[0];
//			int likeCount = (int) tuple[1];
//			int year = (int) tuple[2];
//			int month = (int) tuple[3];
//			String continent = (String) tuple[4];
//			return new LdbcSnbBiQuery24MessagesByTopicResult(messageCount, likeCount, year, month, continent);
//		}
//	}
//
//	public static class BiQuery25 extends CypherListOperationHandler<LdbcSnbBiQuery25WeightedPaths, LdbcSnbBiQuery25WeightedPathsResult, CypherBiQueryStore> {
//
//		@Override
//		public String getQueryString(CypherDriverConnectionStore<CypherBiQueryStore> state, LdbcSnbBiQuery25WeightedPaths operation) {
//			return state.getQueryStore().getQuery25(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery25WeightedPathsResult convertSingleResult(Object[] tuple) {
//			List<Long> personIds = record.get(0).asList(Values.ofLong());
//			return new LdbcSnbBiQuery25WeightedPathsResult(personIds);
//		}
//	}
}
