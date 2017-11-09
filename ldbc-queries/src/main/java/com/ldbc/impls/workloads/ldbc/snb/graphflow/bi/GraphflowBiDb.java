package com.ldbc.impls.workloads.ldbc.snb.graphflow.bi;

import ca.waterloo.dsg.graphflow.query.QueryProcessor;
import ca.waterloo.dsg.graphflow.query.result.QueryResult;
import ca.waterloo.dsg.graphflow.server.ServerQueryString;
import com.google.common.collect.ImmutableMap;
import com.ldbc.driver.DbException;
import com.ldbc.driver.control.LoggingService;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery11UnrelatedReplies;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery11UnrelatedRepliesResult;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery12TrendingPosts;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery12TrendingPostsResult;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery17FriendshipTriangles;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery17FriendshipTrianglesResult;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery4PopularCountryTopics;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery4PopularCountryTopicsResult;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery7AuthoritativeUsers;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery7AuthoritativeUsersResult;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery9RelatedForums;
import com.ldbc.driver.workloads.ldbc.snb.bi.LdbcSnbBiQuery9RelatedForumsResult;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.GraphflowDb;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.GraphflowDriverConnectionStore;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.GraphflowListOperationHandler;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.GraphflowPoolingDbConnectionStore;
import com.ldbc.impls.workloads.ldbc.snb.graphflow.GraphflowSingletonOperationHandler;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GraphflowBiDb extends GraphflowDb {

	private static final String PREFIX = "/home/szarnyasg/git/ldbc/snb/datagen/social_network/";
	private static final String POSTFIX = "_0_0.csv";

	final Map<String, Optional<String>> nodeCsvs = ImmutableMap.<String, Optional<String>>builder() //
			.put("comment", Optional.of("Comment")) //
			.put("forum", Optional.of("Forum")) //
			.put("organisation", Optional.empty()) //
			.put("person", Optional.of("Person")) //
			.put("place", Optional.of("Place")) //
			.put("post", Optional.of("Post")) //
			.put("tagclass", Optional.of("TagClass")) //
			.put("tag", Optional.of("Tag")) //
			.build();

	final Map<String, String> relationshipCsvs = ImmutableMap.<String, String>builder() //
			.put("comment_hasCreator_person", "hasCreator") //
			.put("comment_isLocatedIn_place", "isLocatedIn") //
			.put("comment_replyOf_comment", "replyOf") //
			.put("comment_replyOf_post", "replyOf") //
			.put("forum_containerOf_post", "containerOf") //
			.put("forum_hasMember_person", "hasMember") //
			.put("forum_hasModerator_person", "hasModerator") //
			.put("forum_hasTag_tag", "hasTag") //
			.put("person_hasInterest_tag", "hasInterest") //
			.put("person_isLocatedIn_place", "isLocatedIn") //
			.put("person_knows_person", "knows") //
			.put("person_likes_comment", "likes") //
			.put("person_likes_post", "likes") //
			.put("place_isPartOf_place", "isPartOf") //
			.put("post_hasCreator_person", "hasCreator") //
			.put("comment_hasTag_tag", "hasTag") //
			.put("post_hasTag_tag", "hasTag") //
			.put("post_isLocatedIn_place", "isLocatedIn") //
			.put("tagclass_isSubclassOf_tagclass", "isSubclassOf") //
			.put("tag_hasType_tagclass", "hasType") //
			.put("organisation_isLocatedIn_place", "isLocatedIn") //
			.put("person_studyAt_organisation", "studyAt") //
			.put("person_workAt_organisation", "workAt") //
			.build();

	protected <T> Map<String, T> addPreAndPostFix(Map<String, T> names) {
		return names.entrySet()
				.stream()
				.collect(Collectors.toMap(
						e -> PREFIX + e.getKey() + POSTFIX,
						e -> e.getValue())
				);
	}

	final Map<String, Optional<String>> nodeFilenames = addPreAndPostFix(nodeCsvs);
	final Map<String, String> relationshipFilenames = addPreAndPostFix(relationshipCsvs);

	@Override
	protected void onInit(Map<String, String> properties, LoggingService loggingService) throws DbException {
		dbs = new GraphflowPoolingDbConnectionStore(properties, new GraphflowBiQueryStore(properties.get("queryDir")));

		processor = dbs.getQueryProcessor();

		final String loadVerticesFormat = "load vertices %s from csv '%s' separator '|';";
		final String loadEdgesFormat    = "load edges    with type  '%s' from csv '%s' separator '|';";

		for (Map.Entry<String, Optional<String>> entry : nodeFilenames.entrySet()) {
			final String fileName = entry.getKey();
			final Optional<String> vertexLabel = entry.getValue();

			final String filename = String.format(fileName, vertexLabel);
			final String loadCommand = String.format(
					loadVerticesFormat,
					vertexLabel.isPresent() ? "with label '" + vertexLabel.get() + "'" : "",
					filename);

			System.out.println(loadCommand);
			runCypher(processor, loadCommand);
		}
		for (Map.Entry<String, String> entry : relationshipFilenames.entrySet()) {
			final String fileName = entry.getKey();
			final String edgeType = entry.getValue();

			final String filename = String.format(fileName, edgeType);
			final String loadCommand = String.format(loadEdgesFormat, edgeType, filename);
			System.out.println(loadCommand);
			runCypher(processor, loadCommand);
		}

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

//	public static class BiQuery1 extends GraphflowListOperationHandler<LdbcSnbBiQuery1PostingSummary, LdbcSnbBiQuery1PostingSummaryResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery1PostingSummary operation) {
//			return state.getQueryStore().getQuery1(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery1PostingSummaryResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNames) {
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
//	public static class BiQuery2 extends GraphflowListOperationHandler<LdbcSnbBiQuery2TopTags, LdbcSnbBiQuery2TopTagsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery2TopTags operation) {
//			return state.getQueryStore().getQuery2(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery2TopTagsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
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
//	public static class BiQuery3 extends GraphflowListOperationHandler<LdbcSnbBiQuery3TagEvolution, LdbcSnbBiQuery3TagEvolutionResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery3TagEvolution operation) {
//			return state.getQueryStore().getQuery3(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery3TagEvolutionResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			String tagName = (String) tuple[0];
//			int countA = (int) tuple[1];
//			int countB = (int) tuple[2];
//			int diffCount = (int) tuple[3];
//			return new LdbcSnbBiQuery3TagEvolutionResult(tagName, countA, countB, diffCount);
//		}
//
//	}

	public static class BiQuery4 extends GraphflowListOperationHandler<LdbcSnbBiQuery4PopularCountryTopics, LdbcSnbBiQuery4PopularCountryTopicsResult, GraphflowBiQueryStore> {

		@Override
		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery4PopularCountryTopics operation) {
			return state.getQueryStore().getQuery4(operation);
		}

		@Override
		public LdbcSnbBiQuery4PopularCountryTopicsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
			long forumId = (long) tuple[columnNamesMap.get("forumId")];
			String title = (String) tuple[columnNamesMap.get("title")];
			long creationDate = 0L; // TODO get year, month and day instead of 'new Converter().convertTimestampToEpoch((String) tuple[2]);'
			long moderator = (long) tuple[columnNamesMap.get("moderator")];
			int count = (int) tuple[columnNamesMap.get("count")];
			return new LdbcSnbBiQuery4PopularCountryTopicsResult(forumId, title, creationDate, moderator, count);
		}
	}

//	public static class BiQuery5 extends GraphflowListOperationHandler<LdbcSnbBiQuery5TopCountryPosters, LdbcSnbBiQuery5TopCountryPostersResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery5TopCountryPosters operation) {
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
//	public static class BiQuery6 extends GraphflowListOperationHandler<LdbcSnbBiQuery6ActivePosters, LdbcSnbBiQuery6ActivePostersResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery6ActivePosters operation) {
//			return state.getQueryStore().getQuery6(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery6ActivePostersResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			int postCount = (int) tuple[1];
//			int replyCount = (int) tuple[2];
//			int likeCount = (int) tuple[3];
//			int score = (int) tuple[4];
//			return new LdbcSnbBiQuery6ActivePostersResult(personId, postCount, replyCount, likeCount, score);
//		}
//
//	}

	public static class BiQuery7 extends GraphflowListOperationHandler<LdbcSnbBiQuery7AuthoritativeUsers, LdbcSnbBiQuery7AuthoritativeUsersResult, GraphflowBiQueryStore> {

		@Override
		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery7AuthoritativeUsers operation) {
			return state.getQueryStore().getQuery7(operation);
		}

		@Override
		public LdbcSnbBiQuery7AuthoritativeUsersResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
			long personId = (long) tuple[columnNamesMap.get("personId")];
			int score = (int) tuple[columnNamesMap.get("score")];
			return new LdbcSnbBiQuery7AuthoritativeUsersResult(personId, score);
		}

	}

//	public static class BiQuery8 extends GraphflowListOperationHandler<LdbcSnbBiQuery8RelatedTopics, LdbcSnbBiQuery8RelatedTopicsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery8RelatedTopics operation) {
//			return state.getQueryStore().getQuery8(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery8RelatedTopicsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			String tag = (String) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery8RelatedTopicsResult(tag, count);
//		}
//
//	}

	public static class BiQuery9 extends GraphflowListOperationHandler<LdbcSnbBiQuery9RelatedForums, LdbcSnbBiQuery9RelatedForumsResult, GraphflowBiQueryStore> {

		@Override
		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery9RelatedForums operation) {
			return state.getQueryStore().getQuery9(operation);
		}

		@Override
		public LdbcSnbBiQuery9RelatedForumsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
			long forumId = (long) tuple[columnNamesMap.get("forumId")];
			int sumA = (int) tuple[columnNamesMap.get("sumA")];
			int sumB = (int) tuple[columnNamesMap.get("sumB")];
			return new LdbcSnbBiQuery9RelatedForumsResult(forumId, sumA, sumB);
		}

	}

//	public static class BiQuery10 extends GraphflowListOperationHandler<LdbcSnbBiQuery10TagPerson, LdbcSnbBiQuery10TagPersonResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery10TagPerson operation) {
//			return state.getQueryStore().getQuery10(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery10TagPersonResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			int score = (int) tuple[1];
//			int friendsScore = (int) tuple[2];
//			return new LdbcSnbBiQuery10TagPersonResult(personId, score, friendsScore);
//		}
//
//	}

	public static class BiQuery11 extends GraphflowListOperationHandler<LdbcSnbBiQuery11UnrelatedReplies, LdbcSnbBiQuery11UnrelatedRepliesResult, GraphflowBiQueryStore> {

		@Override
		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery11UnrelatedReplies operation) {
			return state.getQueryStore().getQuery11(operation);
		}

		@Override
		public LdbcSnbBiQuery11UnrelatedRepliesResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
			long personId = (long) tuple[columnNamesMap.get("personId")];
			String tagName = (String) tuple[columnNamesMap.get("tagName")];
			int countLikes = (int) tuple[columnNamesMap.get("count(fan)")];
			int countReplies = (int) tuple[columnNamesMap.get("count(reply)")];
			return new LdbcSnbBiQuery11UnrelatedRepliesResult(personId, tagName, countLikes, countReplies);
		}

	}

	public static class BiQuery12 extends GraphflowListOperationHandler<LdbcSnbBiQuery12TrendingPosts, LdbcSnbBiQuery12TrendingPostsResult, GraphflowBiQueryStore> {

		@Override
		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery12TrendingPosts operation) {
			return state.getQueryStore().getQuery12(operation);
		}

		@Override
		public LdbcSnbBiQuery12TrendingPostsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
			long personId = (long) tuple[columnNamesMap.get("personId")];
			String firstName = (String) tuple[columnNamesMap.get("firstName")];
			String lastName = (String) tuple[columnNamesMap.get("lastName")];
			// TODO reconstruct the date from the results

//			long creationDate = new Converter().convertTimestampToEpoch((String) tuple[3]);
			int likeCount = (int) tuple[columnNamesMap.get("likeCount")];
			return new LdbcSnbBiQuery12TrendingPostsResult(personId, firstName, lastName, 0L, likeCount);
		}
	}

//	public static class BiQuery13 extends GraphflowListOperationHandler<LdbcSnbBiQuery13PopularMonthlyTags, LdbcSnbBiQuery13PopularMonthlyTagsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery13PopularMonthlyTags operation) {
//			return state.getQueryStore().getQuery13(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery13PopularMonthlyTagsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
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
//	public static class BiQuery14 extends GraphflowListOperationHandler<LdbcSnbBiQuery14TopThreadInitiators, LdbcSnbBiQuery14TopThreadInitiatorsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery14TopThreadInitiators operation) {
//			return state.getQueryStore().getQuery14(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery14TopThreadInitiatorsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			String firstName = (String) tuple[1];
//			String lastName = (String) tuple[2];
//			int count = (int) tuple[3];
//			int threadCount = (int) tuple[4];
//			return new LdbcSnbBiQuery14TopThreadInitiatorsResult(personId, firstName, lastName, count, threadCount);
//		}
//	}
//
//	public static class BiQuery15 extends GraphflowListOperationHandler<LdbcSnbBiQuery15SocialNormals, LdbcSnbBiQuery15SocialNormalsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery15SocialNormals operation) {
//			return state.getQueryStore().getQuery15(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery15SocialNormalsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery15SocialNormalsResult(personId, count);
//		}
//	}
//
//	public static class BiQuery16 extends GraphflowListOperationHandler<LdbcSnbBiQuery16ExpertsInSocialCircle, LdbcSnbBiQuery16ExpertsInSocialCircleResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery16ExpertsInSocialCircle operation) {
//			return state.getQueryStore().getQuery16(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery16ExpertsInSocialCircleResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			String tag = (String) tuple[1];
//			int count = (int) tuple[2];
//			return new LdbcSnbBiQuery16ExpertsInSocialCircleResult(personId, tag, count);
//		}
//	}

	public static class BiQuery17 extends GraphflowSingletonOperationHandler<LdbcSnbBiQuery17FriendshipTriangles, LdbcSnbBiQuery17FriendshipTrianglesResult, GraphflowBiQueryStore> {

		@Override
		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery17FriendshipTriangles operation) {
			return state.getQueryStore().getQuery17(operation);
		}

		@Override
		public LdbcSnbBiQuery17FriendshipTrianglesResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
			int count = (int) tuple[columnNamesMap.get("count")];
			return new LdbcSnbBiQuery17FriendshipTrianglesResult(count);
		}
	}

//	public static class BiQuery18 extends GraphflowListOperationHandler<LdbcSnbBiQuery18PersonPostCounts, LdbcSnbBiQuery18PersonPostCountsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery18PersonPostCounts operation) {
//			return state.getQueryStore().getQuery18(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery18PersonPostCountsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			int postCount = (int) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery18PersonPostCountsResult(postCount, count);
//		}
//	}
//
//
//	public static class BiQuery19 extends GraphflowListOperationHandler<LdbcSnbBiQuery19StrangerInteraction, LdbcSnbBiQuery19StrangerInteractionResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery19StrangerInteraction operation) {
//			return state.getQueryStore().getQuery19(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery19StrangerInteractionResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			int strangerCount = (int) tuple[1];
//			int count = (int) tuple[2];
//			return new LdbcSnbBiQuery19StrangerInteractionResult(personId, strangerCount, count);
//		}
//	}
//
//	public static class BiQuery20 extends GraphflowListOperationHandler<LdbcSnbBiQuery20HighLevelTopics, LdbcSnbBiQuery20HighLevelTopicsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery20HighLevelTopics operation) {
//			return state.getQueryStore().getQuery20(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery20HighLevelTopicsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			String tagClass = (String) tuple[0];
//			int count = (int) tuple[1];
//			return new LdbcSnbBiQuery20HighLevelTopicsResult(tagClass, count);
//		}
//	}
//
//	public static class BiQuery21 extends GraphflowListOperationHandler<LdbcSnbBiQuery21Zombies, LdbcSnbBiQuery21ZombiesResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery21Zombies operation) {
//			return state.getQueryStore().getQuery21(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery21ZombiesResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personId = (long) tuple[0];
//			int zombieCount = (int) tuple[1];
//			int realCount = (int) tuple[2];
//			double score = (double) tuple[3];
//			return new LdbcSnbBiQuery21ZombiesResult(personId, zombieCount, realCount, score);
//		}
//	}
//
//	public static class BiQuery22 extends GraphflowListOperationHandler<LdbcSnbBiQuery22InternationalDialog, LdbcSnbBiQuery22InternationalDialogResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery22InternationalDialog operation) {
//			return state.getQueryStore().getQuery22(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery22InternationalDialogResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			long personIdA = (long) tuple[0];
//			long personIdB = (long) tuple[1];
//			String cityName = (String) tuple[2];
//			int  score = (int) tuple[3];
//			return new LdbcSnbBiQuery22InternationalDialogResult(personIdA, personIdB, score);
//		}
//	}
//
//	public static class BiQuery23 extends GraphflowListOperationHandler<LdbcSnbBiQuery23HolidayDestinations, LdbcSnbBiQuery23HolidayDestinationsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery23HolidayDestinations operation) {
//			return state.getQueryStore().getQuery23(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery23HolidayDestinationsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			int count = (int) tuple[0];
//			String countryName = (String) tuple[1];
//			int month = (int) tuple[2];
//			return new LdbcSnbBiQuery23HolidayDestinationsResult(countryName, month, count);
//		}
//	}
//
//
//	public static class BiQuery24 extends GraphflowListOperationHandler<LdbcSnbBiQuery24MessagesByTopic, LdbcSnbBiQuery24MessagesByTopicResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery24MessagesByTopic operation) {
//			return state.getQueryStore().getQuery24(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery24MessagesByTopicResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			int messageCount = (int) tuple[0];
//			int likeCount = (int) tuple[1];
//			int year = (int) tuple[2];
//			int month = (int) tuple[3];
//			String continent = (String) tuple[4];
//			return new LdbcSnbBiQuery24MessagesByTopicResult(messageCount, likeCount, year, month, continent);
//		}
//	}
//
//	public static class BiQuery25 extends GraphflowListOperationHandler<LdbcSnbBiQuery25WeightedPaths, LdbcSnbBiQuery25WeightedPathsResult, GraphflowBiQueryStore> {
//
//		@Override
//		public String getQueryString(GraphflowDriverConnectionStore<GraphflowBiQueryStore> state, LdbcSnbBiQuery25WeightedPaths operation) {
//			return state.getQueryStore().getQuery25(operation);
//		}
//
//		@Override
//		public LdbcSnbBiQuery25WeightedPathsResult convertSingleResult(Object[] tuple, Map<String, Integer> columnNamesMap) {
//			List<Long> personIds = record.get(0).asList(Values.ofLong());
//			return new LdbcSnbBiQuery25WeightedPathsResult(personIds);
//		}
//	}

	public static QueryResult runCypher(QueryProcessor qp, String cypher) {
		final ServerQueryString serverQueryString = ServerQueryString.newBuilder().setQuery(cypher).build();
		final QueryResult result = qp.process(serverQueryString);
		System.out.println(result);
		return result;
	}

}
