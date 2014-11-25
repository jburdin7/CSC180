package labs.five;

import java.io.IOException;
import java.util.Collection;

public interface DataSource {
	Collection<Auction> getAuctions() throws IOException;
	Auction create(Auction auction) throws IOException;
	void delete(Long id) throws IOException;
	void update(Auction auction, Long id) throws IOException;
	Auction retrieve(Long id);
}
