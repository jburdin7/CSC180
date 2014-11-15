package labs.four;

import java.io.IOException;

public interface DataSource {
	Auction[] getAuctions() throws IOException;
	Auction create(Auction auction) throws IOException;
	Auction retrieve();
	Auction delete(Long id);
	Auction update(Auction auction, Long id);
}
