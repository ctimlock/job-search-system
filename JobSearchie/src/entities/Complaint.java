package entities;
public class Complaint
{
    private String complaintID;
    public Complaint()
    {
        complaintID = "00000";
    }

    public Complaint(String complaintID)
    {
        this.complaintID = complaintID;
    }

    public String getComplaintID()
    {
        return complaintID;
    }

    public void setComplaintID(String complaintID)
    {
        this.complaintID = complaintID;
    }
}
