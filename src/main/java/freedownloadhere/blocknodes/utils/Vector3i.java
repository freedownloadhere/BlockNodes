package freedownloadhere.blocknodes.utils;

public class Vector3i
{
    private int X = 0, Y = 0, Z = 0;

    public Vector3i() { }
    public Vector3i(int x, int y, int z) { X = x; Y = y; Z = z; }

    public int Hash()
    {
        return X * 73856093 ^ Y * 19349663 ^ Z * 83492791;
    }

    public String ToString()
    {
        return Integer.toString(X) + ' ' + Integer.toString(Y) + ' ' + Integer.toString(Z);
    }

    public boolean equals(Vector3i other)
    {
        return (X == other.X) && (Y == other.Y) && (Z == other.Z);
    }
}
