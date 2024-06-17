package interactions.backend.service;

import interactions.backend.search.SearchRequest;
import interactions.backend.search.SearchResponse;


public interface InteractionSearch {

    public SearchResponse searchInteractions(SearchRequest searchRequest);
}

