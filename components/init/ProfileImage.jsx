const ProfileImage = ({ src, alt }) => (
    <div style={{ flexShrink: 0 }}>
        <img
            src={src}
            alt={alt}
            style={{
                width: "85%",
                height: "auto",
                borderRadius: "50%",
                objectFit: "cover",
                border: "5px solid #ddd",
            }}
        />
    </div>
);

export default ProfileImage;