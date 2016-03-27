package chunk;

import java.io.Serializable;

/**
 * Created by Luis on 08/03/2016.
 */
public class Chunk implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fileId;
    private Integer chunkNo;
    private Integer replicationDegree;
    private Integer knownReplications;
    private Boolean sent;


    public Chunk(String file, Integer chunk, Integer repDegree) {
        fileId = file;
        chunkNo = chunk;
        replicationDegree = repDegree;
        knownReplications = 0;
        sent = false;
    }

    public String getFileId() {
        return fileId;
    }

    public synchronized void setFileId(String newFileId) {
        fileId = newFileId;
    }

    public Integer getChunkNo() {
        return chunkNo;
    }

    public synchronized void setChunkNo(Integer currentChunkNo) {
        chunkNo = currentChunkNo;
    }

    public Integer getRepDegree() {
        return replicationDegree;
    }

    public synchronized void setRepDegree(Integer currentReplicationDegree) {
        replicationDegree = currentReplicationDegree;
    }

    public int getKnownReps() {
        return knownReplications;
    }

    public synchronized void setKnownReps(int currentKnowReplications) {
        knownReplications += currentKnowReplications;
    }

    Integer getCurrentReps() {
        return knownReplications - replicationDegree;
    }
}
