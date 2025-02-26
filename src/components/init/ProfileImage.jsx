const ProfileImage = ({ src, alt }) => (
    <div style={{ flexShrink: 0, marginRight: "30px" }}>
        <img
            src={src}
            alt={alt}
            style={{
                width: "250px",
                height: "250px",
                borderRadius: "50%",
                objectFit: "cover",
                border: "5px solid #ddd",
            }}
        />
    </div>
);

export default ProfileImage;